package com.bankaccount.metier;

import java.time.LocalDateTime;

import com.bankaccount.exception.AccountException;
import com.bankaccount.exception.AmountException;
import com.bankaccount.exception.TransactionException;
import com.bankaccount.persistance.Statement;
import com.bankaccount.service.Iprinter;

public class Account {

	private Statement statement;

	/**
	 * Constructor
	 */
	public Account(Amount amount) throws AccountException, AmountException, TransactionException {
		if (amount == null) {
			throw new AccountException("The initial balance cannot be null");
		}
		this.statement = new Statement();
		this.statement.addDeposit(amount, LocalDateTime.now());
	}

	/*
	 * Method to print statement
	 */
	public void print(final Iprinter print) {
		this.statement.print(print);
	}

	/**
	 * Method to get total balance
	 */
	public Amount totalBalance() throws AmountException, TransactionException {
		return this.statement.totalBalance();
	}

	/*
	 * Method to deposit an amount
	 */
	public void deposit(final Amount amount) throws AccountException, AmountException, TransactionException {
		if (amount == null) {
			throw new AccountException("deposited amount should not be null");
		}

		this.statement.addDeposit(amount, LocalDateTime.now());
	}

	/*
	 * Method to withdrawal an amount
	 */
	public void withdrawal(final Amount amount) throws AccountException, AmountException, TransactionException {
		if (amount == null) {
			throw new AccountException("withdrawal amount should not be null");
		}

		this.statement.addWithdrawal(amount, LocalDateTime.now());
	}

	public Statement statement() {
		return this.statement;
	}

}
