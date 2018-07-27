package com.bankaccount;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bankaccount.exception.AccountException;
import com.bankaccount.exception.AmountException;
import com.bankaccount.exception.TransactionException;
import com.bankaccount.metier.Account;
import com.bankaccount.metier.Amount;

public class AcceptanceTest {

	@Test
	public void should_generate_a_transaction_history() throws AccountException, AmountException, TransactionException {

		/*
		 * Given
		 */
		List<String> statements = new ArrayList<>();
		String toDay = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
		Account account = new Account(new Amount(200.4f));

		/*
		 * When
		 */
		account.deposit(new Amount(6384.6f));
		account.withdrawal(new Amount(500f));
		account.print(statements::add);

		/*
		 * Then
		 */
		assertThat(statements).isNotEmpty();
		assertThat(statements).hasSize(4);
		assertThat(statements.get(0)).isEqualTo(" OPERATION TYPE          AMOUNT         BALANCE  OPERATION DATE");
		assertThat(statements.get(1)).isEqualTo("        DEPOSIT           200.4             0.0      " + toDay + "");
		assertThat(statements.get(2)).isEqualTo("        DEPOSIT          6384.6           200.4      " + toDay + "");
		assertThat(statements.get(3)).isEqualTo("      WITHDRAWL           500.0          6585.0      " + toDay + "");
	}
}
