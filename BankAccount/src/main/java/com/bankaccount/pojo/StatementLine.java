package com.bankaccount.pojo;

import org.apache.commons.lang3.Validate;

import com.bankaccount.persistance.TransactionPersistance;

public class StatementLine {

	private TransactionPersistance transaction;
	private Amount balance;

	/**
	 * Constructor
	 */
	public StatementLine(TransactionPersistance transaction, Amount balance) {
		Validate.notNull(transaction, "transaction should be not null");
		Validate.notNull(balance, "balance should be not null");
		this.transaction = transaction;
		this.balance = balance;
	}

	public TransactionPersistance transaction() {
		return this.transaction;
	}

	public Amount balance() {
		return this.balance;
	}

}
