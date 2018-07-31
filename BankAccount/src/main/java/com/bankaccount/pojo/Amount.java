package com.bankaccount.pojo;

import java.util.Objects;

import org.apache.commons.lang3.Validate;

public class Amount {

	private Float value;

	/**
	 * Constructor
	 */
	public Amount(Float value) {
		Validate.notNull(value, "The amount cannot be null");
		this.value = value;
	}

	public Float value() {
		return this.value;
	}

	public String toString() {
		return this.value.toString();
	}

	public boolean equals(final Object object) {
		Amount amount = (Amount) object;
		return Objects.equals(this.value, amount.value);
	}

}
