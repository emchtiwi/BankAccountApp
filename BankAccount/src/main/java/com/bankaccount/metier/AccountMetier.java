package com.bankaccount.metier;

import java.time.LocalDateTime;

import org.apache.commons.lang3.Validate;

import com.bankaccount.persistance.TransactionPersistance;
import com.bankaccount.pojo.Amount;
import com.bankaccount.service.PrinterService;
import com.bankaccount.utils.Operation;

public class AccountMetier {

	private StatementMetier statementMetier;

	private Amount balance = AmountMetier.toAmount(0f);

	/**
	 * Constructor
	 */
	public AccountMetier(StatementMetier statementMetier) {
		Validate.notNull(statementMetier, "The Statement cannot be null");
		this.statementMetier = statementMetier;
	}

	/*
	 * Method to deposit an amount
	 */
	public void deposit(final Amount amount) {
		Validate.notNull(amount, "deposited amount should not be null");
		saveTransaction(Operation.DEPOSIT, amount);
	}

	/*
	 * Method to withdraw an amount
	 */
	public void withdrawal(final Amount amount) {
		Validate.notNull(amount, "withdrawal amount should not be null");
		Amount negativeAmount = AmountMetier.subtract(this.balance, amount);
		saveTransaction(Operation.WITHDRAWL, negativeAmount);
	}

	/*
	 * Method to print the statement
	 */
	public void print(final PrinterService print) {
		Validate.notNull(print, "print should not be null");
		this.statementMetier.print(print);
	}

	/**
	 * Method to save transaction
	 */
	private void saveTransaction(final Operation operation, final Amount amount) {
		TransactionPersistance transaction = new TransactionPersistance(operation, LocalDateTime.now(), amount);
		Amount totalBalance = transaction.totalBalance(this.balance);
		this.balance = totalBalance;
		this.statementMetier.saveTransaction(transaction, this.balance);
	}
}
