package com.bankaccount;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;

import com.bankaccount.persistance.TransactionPersistance;
import com.bankaccount.pojo.Amount;
import com.bankaccount.utils.Operation;

public class TransactionTest {

	@Test(expected = NullPointerException.class)
	public void amount_should_be_not_null() {

		new TransactionPersistance(Operation.DEPOSIT, LocalDateTime.now(), null);

	}

	@Test(expected = NullPointerException.class)
	public void operation_type_should_be_not_null() {

		new TransactionPersistance(null, LocalDateTime.now(), new Amount(100f));

	}

	@Test(expected = NullPointerException.class)
	public void date_should_be_not_null() {

		new TransactionPersistance(Operation.DEPOSIT, null, new Amount(100f));

	}

	@Test
	public void verify_to_print_deposit() {

		/**
		 * Given
		 */
		TransactionPersistance transaction = new TransactionPersistance(Operation.DEPOSIT,
				LocalDateTime.of(2000, 1, 1, 0, 0), new Amount(100f));

		/**
		 * When
		 */
		String print = transaction.print(new Amount(100f));

		/**
		 * Then
		 */
		assertThat(print)
				.isEqualToIgnoringNewLines("        DEPOSIT           100.0           100.0      2000-01-01");

	}

	@Test
	public void verify_to_print_withdrawal() {

		/**
		 * Given
		 */
		TransactionPersistance transaction = new TransactionPersistance(Operation.WITHDRAWL,
				LocalDateTime.of(2000, 1, 1, 0, 0), new Amount(200f));

		/**
		 * When
		 */
		String print = transaction.print(new Amount(0f));

		/**
		 * Then
		 */
		assertThat(print)
				.isEqualToIgnoringWhitespace("        WITHDRAWL           200.0           0.0      2000-01-01");

	}

	@Test
	public void verify_calculate_balance() {

		/**
		 * Given
		 */
		TransactionPersistance transaction = new TransactionPersistance(Operation.WITHDRAWL,
				LocalDateTime.of(2000, 1, 1, 0, 0), new Amount(-100f));

		/**
		 * when
		 */
		Amount balance = transaction.totalBalance(new Amount(100f));

		/**
		 * Then
		 */
		assertThat(balance).isEqualTo(new Amount(0f));

	}

}
