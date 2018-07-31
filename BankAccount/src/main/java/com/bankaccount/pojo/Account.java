package com.bankaccount.pojo;

import org.apache.commons.lang3.Validate;

public class Account {

	private Amount amount;

	/**
	 * Constructor
	 */
	public Account(Amount amount) {
		Validate.notNull(amount, "The initial balance cannot be null");
		this.amount = amount;
	}

	public Amount amount() {
		return this.amount;
	}

}
