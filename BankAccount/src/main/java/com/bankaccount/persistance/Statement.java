package com.bankaccount.persistance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bankaccount.exception.AmountException;
import com.bankaccount.exception.StatementException;
import com.bankaccount.exception.TransactionException;
import com.bankaccount.metier.*;
import com.bankaccount.service.Iprinter;
import com.bankaccount.util.Operation;

public class Statement {

	private static Logger logger = Logger.getLogger(Statement.class);

	private List<Transaction> transactions;

	/*
	 * Constructor without parameters
	 */
	public Statement() {
		this.transactions = new ArrayList<>();
	}

	/**
	 * Constructor
	 */
	public Statement(List<Transaction> transactions) throws StatementException {
		if (transactions == null) {
			throw new StatementException("transactions should be not null");
		}
		this.transactions = transactions;
	}

	/*
	 * Method to calculate balance
	 */
	public Amount totalBalance() throws AmountException, TransactionException {
		return this.transactions.stream().reduce((a, b) -> b).map(t -> {
			try {
				return t.calculateBalance();
			} catch (AmountException e) {
				logger.error(e.getMessage());
			}
			return null;
		}).orElse(new Amount(0f));
	}

	/*
	 * Method to print transactions
	 */
	public void print(final Iprinter printer) {
		printer.print(Transaction.HEADER);
		this.transactions.stream().forEach(transaction -> printer.print(transaction.print()));
	}

	/**
	 * Method to add a deposit to the list of transactions
	 */
	public void addDeposit(final Amount amount, final LocalDateTime date) throws AmountException, TransactionException {
		Transaction transaction = new Transaction(Operation.DEPOSIT, date, amount, totalBalance());
		this.transactions.add(transaction);
	}

	/*
	 * Method to add a withdrawal to the list of transactions
	 */
	public void addWithdrawal(final Amount amount, final LocalDateTime date)
			throws AmountException, TransactionException {
		Transaction transaction = new Transaction(Operation.WITHDRAWL, date, amount, totalBalance());
		this.transactions.add(transaction);
	}

}
