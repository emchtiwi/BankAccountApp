package com.bankaccount;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.bankaccount.exception.AccountException;
import com.bankaccount.exception.AmountException;
import com.bankaccount.exception.TransactionException;
import com.bankaccount.metier.Account;
import com.bankaccount.metier.Amount;

public class AccountTest {

	@Test(expected = AccountException.class)
	public void balance_should_not_be_null() throws AccountException, AmountException, TransactionException {
		new Account(null);
	}

	@Test
	public void statement_should_not_be_null() throws AccountException, AmountException, TransactionException {
		
		/**
		 * Given
		 */
		Account account = new Account(new Amount(100f));

		/**
		 * Then
		 */
		assertThat(account.statement()).isNotNull();

	}

	@Test(expected = AccountException.class)
	public void deposited_amount_should_not_be_null() throws AccountException, AmountException, TransactionException {

		/**
		 * Given
		 */
		Account account = new Account(new Amount(100f));

		/**
		 * When
		 */
		account.deposit(null);
	}

	@Test(expected = AccountException.class)
	public void withdrawal_amount_should_not_be_null() throws AmountException, AccountException, TransactionException {

		/**
		 * Given
		 */
		Account account = new Account(new Amount(100f));

		/**
		 * When
		 */
		account.withdrawal(null);
	}
	@Test()
	public void deposited_amount_should_be_added_to_the_current_balance()
			throws AmountException, AccountException, TransactionException {

		/**
		 * Given
		 */
		Account account = new Account(new Amount(100f));
		Amount amount = new Amount(100f);

		/**
		 * When
		 */
		account.deposit(amount);

		/**
		 * Then
		 */
		assertThat(account.totalBalance()).isEqualTo(new Amount(200f));

	}
	@Test()
	public void withdrawal_amount_should_be_subtracted_from_the_current_amount()
			throws AmountException, AccountException, TransactionException {

		/**
		 * Given
		 */
		Account account = new Account(new Amount(300f));
		Amount amount = new Amount(100f);
		/**
		 * When
		 */
		account.withdrawal(amount);

		/**
		 * Then
		 */
		assertThat(account.totalBalance()).isEqualTo(new Amount(200f));

	}

}
