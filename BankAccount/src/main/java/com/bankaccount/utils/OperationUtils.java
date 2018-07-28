package com.bankaccount.utils;

public enum OperationUtils {

	DEPOSIT("DEPOSIT"),
	WITHDRAWL("WITHDRAWL");

	private String operation;

	OperationUtils(String operation) {
		this.operation = operation;
	}

	public String toString() {
		return operation;
	}

}
