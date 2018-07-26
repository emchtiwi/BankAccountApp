package com.bankaccount.metier;

import java.util.Objects;

import com.bankaccount.exception.AmountException;

public class Amount {

	private Float value;

	public Amount(final Float f) throws AmountException {
		if (f == null) {
			throw new AmountException("The initial amount cannot be null");
		}
		if (f < 0) {
			throw new AmountException("The initial amount cannot be negative");
		}
		value = f;
	}

	public Amount add(final Amount a) throws AmountException {
		if (a == null) {
			throw new AmountException("The added amount cannot be null");
		}
		return new Amount(value + a.value);
	}

	public Amount subtract(final Amount a) throws AmountException {
		if (a == null) {
			throw new AmountException("The subtracted amount cannot be null");
		}
		if (value - a.value <= 0) {
			throw new AmountException("The current amount should be superior or equal to subtracted amount");
		}
		return new Amount(value - a.value);
	}

	public String toString() {
		return value.toString();
	}

	public boolean equals(final Object object) {
		Amount amount = (Amount) object;
		return Objects.equals(value, amount.value);
	}

}
