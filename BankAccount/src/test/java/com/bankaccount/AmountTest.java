package com.bankaccount;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.bankaccount.exception.AmountException;
import com.bankaccount.metier.Amount;

public class AmountTest {

	@Test(expected = AmountException.class)
	public void initial_amount_should_not_be_negative() throws AmountException {
		new Amount(-400f);
	}

	@Test(expected = AmountException.class)
	public void amount_should_be_not_null() throws AmountException {
		new Amount(null);
	}

	@Test(expected = AmountException.class)
	public void current_amount_should_be_superior_or_equal_to_subtracted_amount() throws AmountException {

		/**
		 * Given
		 */
		Amount amount = new Amount(100f);
		Amount amountSubtract = new Amount(300f);

		/**
		 * When
		 */
		amount.subtract(amountSubtract);
	}

	@Test(expected = AmountException.class)
	public void subtracted_amount_should_not_be_null() throws AmountException {

		/**
		 * Given
		 */
		Amount amount = new Amount(100f);

		/**
		 * When
		 */
		amount.subtract(null);
	}

	@Test()
	public void subtracting_an_amount_from_another_should_return_the_difference_between_two() throws AmountException {

		/**
		 * Given
		 */
		Amount amount = new Amount(300f);
		Amount amountSubtract = new Amount(100f);

		/**
		 * When
		 */
		Amount totalAmount = amount.subtract(amountSubtract);

		/**
		 * Then
		 */
		assertThat(totalAmount).isEqualTo(new Amount(200f));

	}

	@Test()
	public void adding_an_amount_to_another_should_return_the_sum_of_both() throws AmountException {

		/**
		 * Given
		 */
		Amount amount = new Amount(100f);
		Amount amountToAdd = new Amount(100f);

		/**
		 * When
		 */
		Amount totalAmount = amount.add(amountToAdd);

		/**
		 * Then
		 */
		assertThat(totalAmount).isEqualTo(new Amount(200f));
	}

	@Test(expected = AmountException.class)
	public void added_amount_should_not_be_null() throws AmountException {

		/**
		 * Given
		 */
		Amount amount = new Amount(100f);

		/**
		 * When
		 */
		amount.add(null);
	}

}
