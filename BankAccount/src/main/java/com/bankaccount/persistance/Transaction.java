package com.bankaccount.persistance;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.bankaccount.exception.AmountException;
import com.bankaccount.exception.TransactionException;
import com.bankaccount.metier.Amount;
import com.bankaccount.util.Operation;

public class Transaction {

	static final String HEADER = String.format("%15s %15s %15s %15s", "OPERATION TYPE", "AMOUNT", "BALANCE",
			"OPERATION DATE");

	private Amount amount;
	private Amount balance;
	private Operation operation;
	private LocalDateTime date;

	/**
	 * Constructor
	 */
	public Transaction(Operation operation, LocalDateTime date, Amount amount, Amount balance)
			throws TransactionException {
		if (operation == null) {
			throw new TransactionException("operation should be not null");
		}
		if (date == null) {
			throw new TransactionException("date should be not null");
		}
		if (amount == null) {
			throw new TransactionException("amount should be not null");
		}
		if (balance == null) {
			throw new TransactionException("balance should be not null");
		}

		if (operation.equals(Operation.WITHDRAWL)) {
			if ((balance.value() - amount.value()) <= 0) {
				throw new TransactionException(
						"Should not save a withdrawal transaction, the amount is superior to the current balance");
			}
		}
		this.amount = amount;
		this.balance = balance;
		this.operation = operation;
		this.date = date;

	}

	/*
	 * Method to format transaction
	 */
	public String print() {
		return String.format("%15s %15s %15s %15s", this.operation.toString(), this.amount, this.balance,
				this.date.format(DateTimeFormatter.ISO_DATE));
	}

	/*
	 * Method to calculate balance
	 */
	public Amount calculateBalance() throws AmountException {
		if (Operation.WITHDRAWL.equals(this.operation)) {
			return this.balance.subtract(this.amount);
		} else {
			return this.balance.add(this.amount);
		}

	}

}
