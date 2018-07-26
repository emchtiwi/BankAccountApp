package com.bankaccount.exception;

public class TransactionException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionException(final String message) {
		super(message);
	}
}
