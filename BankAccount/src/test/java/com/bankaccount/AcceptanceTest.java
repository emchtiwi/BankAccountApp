package com.bankaccount;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bankaccount.metier.Account;
import com.bankaccount.metier.Amount;

import junit.framework.TestCase;

public class AcceptanceTest extends TestCase {

	private String toDayAsString;

	private List<String> statements;

	@Before
	public void initialise() {
		statements = new ArrayList<>();
		toDayAsString = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
	}
	
	@Test
	public void should_generate_a_transaction_statement() {
		/*
		 * Given
		 */
		Account account = new Account(new Amount(200.4f));
	    account.deposit(new Amount(6384.6f));
	    account.withdrawal(new Amount(500f));
	
	    /*
	     * When
	     */
	    account.printStatement(statements::add);

	    /*
	     * Then
	     */
		assertThat(statements.get(0)).isEqualTo(" OPERATION TYPE          AMOUNT         BALANCE  OPERATION DATE");
		assertThat(statements.get(1)).isEqualTo("        DEPOSIT           200.4             0.0      "+toDayAsString+"");
		assertThat(statements.get(1)).isEqualTo("        DEPOSIT          6384.6           200.4      "+toDayAsString+"");
		assertThat(statements.get(2)).isEqualTo("       WITHDRAW           500.0          6585.0      "+toDayAsString+"");
	}

}
