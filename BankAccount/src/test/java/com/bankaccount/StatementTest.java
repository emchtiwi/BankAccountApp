package com.bankaccount;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bankaccount.exception.AmountException;
import com.bankaccount.exception.StatementException;
import com.bankaccount.exception.TransactionException;
import com.bankaccount.metier.Amount;
import com.bankaccount.persistance.Statement;

public class StatementTest {

	private Statement statement;
	private List<String> transactions;

	@Before
	public void initialise() {
		statement = new Statement();
		transactions = new ArrayList<>();
	}

	@Test(expected = StatementException.class)
	public void transactions_should_be_not_null__cons_withParam_() throws StatementException {
		new Statement(null);
	}

	@Test
	public void should_save_a_deposit_transaction() throws AmountException, TransactionException {

		/**
		 * When
		 */
		statement.addDeposit(new Amount(300f), LocalDateTime.of(2000, 1, 1, 0, 0));
		statement.print(transactions::add);

		/**
		 * Then
		 */
		assertThat(transactions).isNotEmpty();
		assertThat(transactions.get(1))
				.isEqualToIgnoringNewLines("        DEPOSIT           300.0             0.0      2000-01-01");
	}

	@Test
	public void should_save_a_withdrawal_transaction() throws AmountException, TransactionException {

		/**
		 * When
		 */
		statement.addDeposit(new Amount(500f), LocalDateTime.of(2000, 1, 1, 0, 0));
		statement.addWithdrawal(new Amount(300f), LocalDateTime.of(2000, 1, 1, 0, 0));
		statement.print(transactions::add);

		/**
		 * Then
		 */
		assertThat(transactions).isNotEmpty();
		assertThat(transactions.get(2))
				.isEqualToIgnoringNewLines("      WITHDRAWL           300.0           500.0      2000-01-01");

	}

	@Test()
	public void verify_current_balance() throws AmountException, TransactionException {

		/**
		 * When
		 */
		statement.addDeposit(new Amount(500f), LocalDateTime.of(2000, 1, 1, 0, 0));

		/**
		 * Then
		 */
		assertThat(statement.totalBalance()).isEqualTo(new Amount(500f));
	}

}
