package com.bankaccount.metier;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bankaccount.exception.AmountException;
import com.bankaccount.exception.StatementException;
import com.bankaccount.service.Iprinter;
import com.bankaccount.util.Operation;

public class Statement {

	List<Transaction> transactions;

	public Statement() {
		transactions = new ArrayList<>();
	}

	public Statement(List<Transaction> transactions) throws StatementException {
		if (transactions == null) {
			throw new StatementException("transactions should be not null");
		}
		this.transactions = transactions;
	}

	/**
	 * Method to add a deposit to the list of transactions
	 */
	public void addDeposit(Amount amount, LocalDateTime date) throws AmountException {
		Transaction transaction = new Transaction(Operation.DEPOSIT, date, amount, totalBalance());
		this.transactions.add(transaction);
	}

	/*
	 * Method to add a withdrawal to the list of transactions
	 */
	public void addWithdrawal(Amount amount, LocalDateTime date) throws AmountException {
		Transaction transaction = new Transaction(Operation.WITHDRAWL, date, amount, totalBalance());
		this.transactions.add(transaction);
	}

	/*
	 * Method to print transactions
	 */
	public void print(Iprinter printer) {
		printer.print(Transaction.HEADER);
		this.transactions.stream().forEach(transaction -> printer.print(transaction.print()));
	}

	/*
	 * Method to calculate balance
	 */
	public Amount totalBalance() throws AmountException {
		return this.transactions.stream().reduce((a, b) -> b).map(Transaction::calculateBalance).orElse(new Amount(0f));
	}

}
