package com.bankaccount.service.metier;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.bankaccount.persistance.TransactionPersistance;
import com.bankaccount.pojo.Amount;
import com.bankaccount.pojo.StatementLine;
import com.bankaccount.service.PrinterService;

public class StatementMetier {

	private String HEADER = String.format("%15s %15s %15s %15s", "OPERATION TYPE", "AMOUNT", "BALANCE",
			"OPERATION DATE");

	private List<StatementLine> statementLines = new ArrayList<StatementLine>();

	/**
	 * Method to save transaction
	 */
	public void saveTransaction(TransactionPersistance transaction, Amount balance) {
		Validate.notNull(transaction, "transaction should be not null");
		Validate.notNull(balance, "balance should be not null");
		StatementLine statementLine = new StatementLine(transaction, balance);
		this.statementLines.add(statementLine);
	}

	/**
	 * Method to print the statement
	 */
	public void print(final PrinterService printer) {
		printer.print(HEADER);
		this.statementLines.forEach(statementLine -> printer.print(printStatementLine(statementLine)));
	}

	/**
	 * Method to print a statement's line
	 */
	private String printStatementLine(final StatementLine statementLine) {
		return statementLine.transaction().print(statementLine.balance());
	}

}
