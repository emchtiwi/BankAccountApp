package com.bankaccount.util;

public enum Operation {

	DEPOSIT("DEPOSIT"),
	WITHDRAWL("WITHDRAWL");

	private String operation;

	Operation(String operation) {
		this.operation = operation;
	}

	public String toString() {
		return operation;
	}

}
