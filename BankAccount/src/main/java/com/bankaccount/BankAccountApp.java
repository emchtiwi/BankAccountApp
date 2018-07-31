package com.bankaccount;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bankaccount.pojo.Amount;
import com.bankaccount.service.metier.AccountMetier;
import com.bankaccount.service.metier.StatementMetier;

public class BankAccountApp {

	private static Logger logger = Logger.getLogger(BankAccountApp.class);

	public static void main(String[] args) {
		List<String> statements = new ArrayList<>();
		AccountMetier accountMetier = new AccountMetier(new StatementMetier());

		/**
		 * US1 & US2 : save and retrieve money
		 */
		accountMetier.deposit(new Amount(200.4f));
		accountMetier.deposit(new Amount(6384.6f));
		accountMetier.withdrawal(new Amount(500f));
		accountMetier.print(statements::add);

		/**
		 * US3 : see the history
		 */
		for (String print : statements) {
			logger.info(print);
		}
	}
}
