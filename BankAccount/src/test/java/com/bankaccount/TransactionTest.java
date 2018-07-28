package com.bankaccount;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;

import com.bankaccount.exception.BankAccountException;
import com.bankaccount.metier.Amount;
import com.bankaccount.persistance.Transaction;
import com.bankaccount.utils.OperationUtils;

public class TransactionTest {

	@Test(expected = BankAccountException.class)
	public void amount_should_be_not_null() throws BankAccountException {

		new Transaction(OperationUtils.DEPOSIT, LocalDateTime.now(), null, new Amount(0f));

	}

	@Test(expected = BankAccountException.class)
	public void operation_type_should_be_not_null() throws BankAccountException {

		new Transaction(null, LocalDateTime.now(), new Amount(0f), new Amount(0f));

	}

	@Test(expected = BankAccountException.class)
	public void balance_should_be_not_null() throws BankAccountException {

		new Transaction(OperationUtils.DEPOSIT, LocalDateTime.now(), new Amount(0f), null);

	}

	@Test(expected = BankAccountException.class)
	public void date_should_be_not_null() throws BankAccountException {

		new Transaction(OperationUtils.DEPOSIT, null, new Amount(0f), new Amount(0f));

	}

	@Test
	public void verify_to_print_deposit() throws BankAccountException {

		/**
		 * When
		 */
		Transaction transaction = new Transaction(OperationUtils.DEPOSIT, LocalDateTime.of(2000, 1, 1, 0, 0),
				new Amount(100f), new Amount(200f));

		/**
		 * Then
		 */
		assertThat(transaction.print())
				.isEqualToIgnoringWhitespace("        DEPOSIT           100.0             200.0      2000-01-01");

	}

	@Test
	public void verify_to_print_withdrawal() throws BankAccountException {

		/**
		 * When
		 */
		Transaction transaction = new Transaction(OperationUtils.WITHDRAWL, LocalDateTime.of(2000, 1, 1, 0, 0),
				new Amount(100f), new Amount(200f));

		/**
		 * Then
		 */
		assertThat(transaction.print())
				.isEqualToIgnoringWhitespace("        WITHDRAWL           100.0             200.0      2000-01-01");

	}

	@Test
	public void verify_calculate_balance() throws BankAccountException {

		/**
		 * When
		 */
		Transaction transaction = new Transaction(OperationUtils.DEPOSIT, LocalDateTime.of(2000, 1, 1, 0, 0),
				new Amount(100f), new Amount(100f));

		/**
		 * Then
		 */
		assertThat(transaction.calculateBalance()).isEqualTo(new Amount(200f));

	}

}
