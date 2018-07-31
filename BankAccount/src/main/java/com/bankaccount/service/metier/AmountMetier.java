package com.bankaccount.service.metier;

import org.apache.commons.lang3.Validate;

import com.bankaccount.pojo.Amount;

public class AmountMetier {

	/*
	 * Method to return a new amount
	 */
	public static  Amount toAmount(final Float value) {
		return new Amount(value);
	}

	/**
	 * Method to return the sum of the balance and the amount
	 */
	public static Amount add(final Amount balance, final Amount amount) {
		return toAmount(balance.value() + amount.value());
	}

	/**
	 * Method to return the negative amount
	 */
	public static Amount subtract(final Amount balance, final Amount amount) {
		Validate.isTrue((amount.value() - balance.value() <= 0),
				"The current amount should be superior or equal to subtracted amount");
		return toAmount(-amount.value());
	}

}
