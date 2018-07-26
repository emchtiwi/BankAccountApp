package com.bankaccount;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;

import com.bankaccount.exception.AmountException;
import com.bankaccount.exception.TransactionException;
import com.bankaccount.metier.Amount;
import com.bankaccount.metier.Transaction;
import com.bankaccount.util.Operation;

public class TransactionTest {

	@Test(expected = TransactionException.class)
	public void amount_should_be_not_null() throws AmountException, TransactionException {

		new Transaction(Operation.DEPOSIT, LocalDateTime.now(), null, new Amount(0f));

	}

	@Test(expected = TransactionException.class)
	public void operation_type_should_be_not_null() throws AmountException, TransactionException {

		new Transaction(null, LocalDateTime.now(), new Amount(0f), new Amount(0f));

	}

	@Test(expected = TransactionException.class)
	public void balance_should_be_not_null() throws AmountException, TransactionException {

		new Transaction(Operation.DEPOSIT, LocalDateTime.now(), new Amount(0f), null);

	}

	@Test(expected = TransactionException.class)
	public void date_should_be_not_null() throws AmountException, TransactionException {

		new Transaction(Operation.DEPOSIT, null, new Amount(0f), new Amount(0f));

	}

	@Test
	public void verify_to_print() throws AmountException, TransactionException {
		
		/**
		 * When
		 */
		Transaction transaction = new Transaction(Operation.DEPOSIT, LocalDateTime.of(2000, 1, 1, 0, 0),
				new Amount(100f), new Amount(200f));
		
		/**
		 * Then
		 */
		assertThat(transaction.print())
				.isEqualToIgnoringWhitespace("        DEPOSIT           100.0             200.0      2000-01-01");

	}

	@Test
	public void verify_calculate_balance() throws AmountException, TransactionException {

		/**
		 * When
		 */
		Transaction transaction = new Transaction(Operation.DEPOSIT, LocalDateTime.of(2000, 1, 1, 0, 0),
				new Amount(100f), new Amount(100f));

		/**
		 * Then
		 */
		assertThat(transaction.calculateBalance()).isEqualTo(new Amount(200f));

	}

}
