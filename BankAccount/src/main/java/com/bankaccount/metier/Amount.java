package com.bankaccount.metier;

import java.util.Objects;

import com.bankaccount.exception.AmountException;

public class Amount {

	private Float value;

	/**
	 * Constructor
	 */
	public Amount(Float value) throws AmountException {
		if (value == null) {
			throw new AmountException("The initial amount cannot be null");
		}
		if (value < 0) {
			throw new AmountException("The initial amount cannot be negative");
		}
		this.value = value;
	}

	/**
	 * Method to add an amount
	 */
	public Amount add(final Amount amount) throws AmountException {
		if (amount == null) {
			throw new AmountException("The added amount cannot be null");
		}
		return new Amount(this.value + amount.value);
	}

	/**
	 * Method to subtract an amount
	 */
	public Amount subtract(final Amount amount) throws AmountException {
		if (amount == null) {
			throw new AmountException("The subtracted amount cannot be null");
		}
		if (this.value - amount.value <= 0) {
			throw new AmountException("The current amount should be superior or equal to subtracted amount");
		}
		return new Amount(this.value - amount.value);
	}

	public String toString() {
		return this.value.toString();
	}

	public boolean equals(final Object object) {
		Amount amount = (Amount) object;
		return Objects.equals(this.value, amount.value);
	}

	public Float value() {
		return this.value;
	}

}
