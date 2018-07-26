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

	public void addDeposit(Amount amount, LocalDateTime date) throws AmountException {
		Transaction transaction = new Transaction(Operation.DEPOSIT, date, amount, totalBalance());
		transactions.add(transaction);
	}

	public void addWithdrawal(Amount amount, LocalDateTime date) throws AmountException {
		Transaction transaction = new Transaction(Operation.WITHDRAWL, date, amount, totalBalance());
		transactions.add(transaction);
	}

	public void print(Iprinter printer) {
		printer.print(Transaction.HEADER);
		transactions.stream().forEach(transaction -> printer.print(transaction.print()));
	}

	public Amount totalBalance() throws AmountException {
		return transactions.stream().reduce((a, b) -> b).map(Transaction::calculateBalance).orElse(new Amount(0f));
	}

}
