package com.bankaccount.persistance;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.Validate;

import com.bankaccount.pojo.Amount;
import com.bankaccount.service.metier.AmountMetier;
import com.bankaccount.utils.Operation;

public class TransactionPersistance {

	private Operation operation;
	private LocalDateTime date;
	private Amount amount;

	/**
	 * Constructor
	 */
	public TransactionPersistance(Operation operation, LocalDateTime date, Amount amount) {
		Validate.notNull(operation, "operation should be not null");
		Validate.notNull(amount, "amount should be not null");
		Validate.notNull(date, "date should be not null");
		this.operation = operation;
		this.date = date;
		this.amount = amount;
	}

	/**
	 * Method to calculate balance
	 */
	public Amount totalBalance(final Amount balance) {
		return AmountMetier.add(balance, this.amount);
	}

	/**
	 * Method to print
	 */
	public String print(Amount balance) {
		return String.format("%15s %15s %15s %15s", this.operation, this.amount, balance,
				this.date.format(DateTimeFormatter.ISO_DATE));
	}

}
