package com.bankaccount.utils;

import com.bankaccount.exception.BankAccountException;

public class CommonUtils {

	/**
	 * Method to check if object Is null and throw Exception
	 */
	public static void checkObjectIsNullThrowException(final Object object, final String message) throws BankAccountException {
		if (object == null) {
			throw new BankAccountException(message);
		}
	}

}
