package com.bankaccount.persistance;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.bankaccount.exception.BankAccountException;
import com.bankaccount.metier.Amount;
import com.bankaccount.utils.CommonUtils;
import com.bankaccount.utils.OperationUtils;

public class Transaction {

	static final String HEADER = String.format("%15s %15s %15s %15s", "OPERATION TYPE", "AMOUNT", "BALANCE",
			"OPERATION DATE");

	private Amount amount;
	private Amount balance;
	private OperationUtils operation;
	private LocalDateTime date;

	/**
	 * Constructor
	 */
	public Transaction(OperationUtils operation, LocalDateTime date, Amount amount, Amount balance)
			throws BankAccountException {

		CommonUtils.checkObjectIsNullThrowException(operation, "operation should be not null");
		CommonUtils.checkObjectIsNullThrowException(date, "date should be not null");
		CommonUtils.checkObjectIsNullThrowException(amount, "amount should be not null");
		CommonUtils.checkObjectIsNullThrowException(balance, "balance should be not null");

		if (operation.equals(OperationUtils.WITHDRAWL)) {
			if ((balance.value() - amount.value()) <= 0) {
				throw new BankAccountException(
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
	public Amount calculateBalance() throws BankAccountException {

		if (OperationUtils.WITHDRAWL.equals(this.operation)) {
			return this.balance.subtract(this.amount);
		} else {
			return this.balance.add(this.amount);
		}

	}

}
