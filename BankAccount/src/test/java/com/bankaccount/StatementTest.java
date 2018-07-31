package com.bankaccount;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bankaccount.persistance.TransactionPersistance;
import com.bankaccount.pojo.Amount;
import com.bankaccount.pojo.StatementLine;
import com.bankaccount.service.metier.StatementMetier;
import com.bankaccount.utils.Operation;

public class StatementTest {

	private StatementMetier statement;

	private List<String> transactions;

	@Before
	public void initialise() {
		statement = new StatementMetier();
		transactions = new ArrayList<>();
	}

	@Test(expected = NullPointerException.class)
	public void StatementLine_should_be_not_null() {

		new StatementLine(null, null);

	}

	@Test(expected = NullPointerException.class)
	public void transaction_should_be_not_null() {

		/**
		 * Given
		 */
		Amount balance = new Amount(100f);

		/**
		 * When
		 */
		statement.saveTransaction(null, balance);
	}

	@Test(expected = NullPointerException.class)
	public void balance_should_be_not_null() {

		/**
		 * Given
		 */
		TransactionPersistance transaction = new TransactionPersistance(Operation.DEPOSIT,
				LocalDateTime.of(2000, 1, 1, 0, 0), new Amount(300f));

		/**
		 * When
		 */
		statement.saveTransaction(transaction, null);
	}

	@Test
	public void should_save_a_deposit_transaction() {

		/**
		 * Given
		 */
		TransactionPersistance transaction = new TransactionPersistance(Operation.DEPOSIT,
				LocalDateTime.of(2000, 1, 1, 0, 0), new Amount(300f));

		/**
		 * When
		 */
		statement.saveTransaction(transaction, new Amount(300f));
		statement.print(transactions::add);

		/**
		 * Then
		 */
		assertThat(transactions).isNotEmpty().hasSize(2);
		assertThat(transactions.get(1))
				.isEqualToIgnoringNewLines("        DEPOSIT           300.0           300.0      2000-01-01");
	}

	@Test
	public void should_save_a_withdrawal_transaction() {

		/**
		 * Given
		 */
		TransactionPersistance transaction = new TransactionPersistance(Operation.WITHDRAWL,
				LocalDateTime.of(2000, 1, 1, 0, 0), new Amount(-100f));

		/**
		 * When
		 */
		statement.saveTransaction(transaction, new Amount(100f));
		statement.print(transactions::add);

		/**
		 * Then
		 */
		assertThat(transactions).isNotEmpty().hasSize(2);
		assertThat(transactions.get(1))
				.isEqualToIgnoringNewLines("      WITHDRAWL          -100.0           100.0      2000-01-01");

	}

}
