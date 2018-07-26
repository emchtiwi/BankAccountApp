package com.bankaccount.metier;

import java.time.LocalDateTime;

import com.bankaccount.exception.AccountException;
import com.bankaccount.service.Iprinter;

public class Account {

	private Statement statement;

	public Account(Amount amount) throws AccountException {
		if (amount == null) {
			throw new AccountException("The initial balance cannot be null");
		}
		this.statement = new Statement();
	}

	/*
	 * Method to deposit an amount
	 */
	public void deposit(Amount amount) throws AccountException {
		if (amount == null) {
			throw new AccountException("deposited amount should not be null");
		}

		this.statement.addDeposit(amount, LocalDateTime.now());
	}

	/*
	 * Method to withdrawal an amount
	 */
	public void withdrawal(Amount amount) throws AccountException {
		if (amount == null) {
			throw new AccountException("withdrawal amount should not be null");
		}

		this.statement.addWithdrawal(amount, LocalDateTime.now());

	}

	/*
	 * Method to print statement
	 */
	public void print(Iprinter print) {
		this.statement.print(print);

	}

	/**
	 * Method to get total balance
	 */
	public Amount totalBalance() {
		return statement.totalBalance();
	}

	Statement statement() {
		return this.statement;
	}

}
