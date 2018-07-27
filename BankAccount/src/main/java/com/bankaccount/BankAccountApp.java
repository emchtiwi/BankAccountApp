package com.bankaccount;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bankaccount.exception.AccountException;
import com.bankaccount.exception.AmountException;
import com.bankaccount.exception.TransactionException;
import com.bankaccount.metier.Account;
import com.bankaccount.metier.Amount;

public class BankAccountApp {

	private static Logger logger = Logger.getLogger(BankAccountApp.class);

	public static void main(String[] args) {
		List<String> statements = new ArrayList<>();
		Account account;

		try {
			
			/**
			 * US1 & US2 : save and retrieve money
			 */
			account = new Account(new Amount(200.4f));
			account.deposit(new Amount(6384.6f));
			account.withdrawal(new Amount(500f));
			account.print(statements::add);

		} catch (AccountException | AmountException | TransactionException e) {
			logger.error(e.getMessage());
		}

		/**
		 * US3 : see the history
		 */
		for (String print : statements) {
			logger.info(print);
		}
	}

}
