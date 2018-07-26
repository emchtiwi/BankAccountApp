package com.bankaccount.metier;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.bankaccount.exception.AmountException;
import com.bankaccount.exception.TransactionException;
import com.bankaccount.util.Operation;

public class Transaction {

	static final String HEADER = String.format("%15s %15s %15s %15s", "OPERATION TYPE", "AMOUNT", "BALANCE",
			"OPERATION DATE");
	private Amount amount;
	private Amount balance;
	private Operation operation;
	private LocalDateTime date;

	public Transaction(Operation operationT, LocalDateTime dateT, Amount amountT, Amount balanceT)
			throws TransactionException {
		if (operationT == null) {
			throw new TransactionException("operation should be not null");
		}
		if (amountT == null) {
			throw new TransactionException("amount should be not null");
		}
		if (balanceT == null) {
			throw new TransactionException("balance should be not null");
		}

		if (operationT.equals(Operation.WITHDRAWL)) {
			if ((balanceT.value() - amountT.value()) <= 0) {
				throw new TransactionException(
						"Should not save a withdrawal transaction, the amount is superior to the current balance");
			}
		}
		amount = amountT;
		balance = balanceT;
		this.operation = operationT;
		this.date = dateT;

	}

	public String print() {
		return String.format("%15s %15s %15s %15s", operation.toString(), amount, balance,
				date.format(DateTimeFormatter.ISO_DATE));
	}

	public Amount calculateBalance() throws AmountException {
		if (Operation.WITHDRAWL.equals(operation)) {
			return balance.subtract(amount);
		} else {
			return balance.add(amount);
		}

	}

}
