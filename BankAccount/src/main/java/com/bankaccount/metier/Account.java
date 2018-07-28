package com.bankaccount.metier;

import java.time.LocalDateTime;

import com.bankaccount.exception.BankAccountException;
import com.bankaccount.persistance.Statement;
import com.bankaccount.service.Iprinter;
import com.bankaccount.utils.CommonUtils;

public class Account {

	private Statement statement;

	/**
	 * Constructor
	 */
	public Account(Amount amount) throws BankAccountException {

		CommonUtils.checkObjectIsNullThrowException(amount, "The initial balance cannot be null");
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
	public Amount totalBalance() throws BankAccountException {
		return this.statement.totalBalance();
	}

	/*
	 * Method to deposit an amount
	 */
	public void deposit(final Amount amount) throws BankAccountException {

		CommonUtils.checkObjectIsNullThrowException(amount, "deposited amount should not be null");
		this.statement.addDeposit(amount, LocalDateTime.now());

	}

	/*
	 * Method to withdrawal an amount
	 */
	public void withdrawal(final Amount amount) throws BankAccountException {

		CommonUtils.checkObjectIsNullThrowException(amount, "withdrawal amount should not be null");
		this.statement.addWithdrawal(amount, LocalDateTime.now());
	}

	public Statement statement() {
		return this.statement;
	}

}
