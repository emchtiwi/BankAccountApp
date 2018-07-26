package com.bankaccount.metier;

import java.time.LocalDateTime;

import com.bankaccount.exception.AccountException;
import com.bankaccount.service.Iprinter;

public class Account {

	private Statement statement;

	public Account(Amount a) throws AccountException {
		if (a == null) {
			throw new AccountException("The initial balance cannot be null");
		}
		statement = new Statement();
	}

	public void deposit(Amount a) throws AccountException {
		if (a == null) {
			throw new AccountException("deposited amount should not be null");
		}

		statement.addDeposit(a, LocalDateTime.now());
	}

	public void withdrawal(Amount a) throws AccountException {
		if (a == null) {
			throw new AccountException("withdrawal amount should not be null");
		}

		statement.addWithdrawal(a, LocalDateTime.now());

	}

	public void print(Iprinter p) {
		statement.print(p);

	}

	public Amount totalBalance() {
		return statement.totalBalance();
	}

}
