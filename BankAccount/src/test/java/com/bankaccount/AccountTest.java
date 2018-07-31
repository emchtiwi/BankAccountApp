package com.bankaccount;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bankaccount.persistance.TransactionPersistance;
import com.bankaccount.pojo.Account;
import com.bankaccount.pojo.Amount;
import com.bankaccount.service.PrinterService;
import com.bankaccount.service.metier.AccountMetier;
import com.bankaccount.service.metier.StatementMetier;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

	private AccountMetier accountMetier;

	private PrinterService print;

	@Mock
	private StatementMetier statementMetier;

	@Before
	public void initialiseAccount() {
		accountMetier = new AccountMetier(statementMetier);
	}

	@Test(expected = NullPointerException.class)
	public void initial_balance_should_not_be_null() {

		new Account(null);

	}

	@Test(expected = NullPointerException.class)
	public void statement_should_not_be_null() {

		new AccountMetier(null);

	}

	@Test(expected = NullPointerException.class)
	public void deposited_amount_should_not_be_null() {

		accountMetier.deposit(null);
	}

	@Test()
	public void deposited_amount_should_be_added_to_the_current_balance() {

		/**
		 * Given
		 */
		Amount balance = new Amount(100F);
		Amount depositAmount = new Amount(100F);

		/**
		 * When
		 */
		accountMetier.deposit(depositAmount);

		/**
		 * Then
		 */
		verify(statementMetier).saveTransaction(any(TransactionPersistance.class), eq(balance));
	}

	@Test(expected = NullPointerException.class)
	public void withdrawal_amount_should_not_be_null() {

		accountMetier.withdrawal(null);

	}

	@Test()
	public void withdrawal_amount_should_be_subtracted_from_the_current_amount() {

		/**
		 * Given
		 */
		Amount depositAmount = new Amount(1000F);
		Amount withdrawAmount = new Amount(400F);
		Amount balance = new Amount(600F);

		/**
		 * When
		 */
		accountMetier.deposit(depositAmount);
		accountMetier.withdrawal(withdrawAmount);

		/**
		 * Then
		 */
		verify(statementMetier).saveTransaction(any(TransactionPersistance.class), eq(balance));

	}

	@Test(expected = NullPointerException.class)
	public void print_should_not_be_null() {

		accountMetier.print(null);

	}

	public void should_print() {

		/**
		 * When
		 */
		accountMetier.print(print);

		/**
		 * Then
		 */
		verify(statementMetier).print(print);

	}

}
