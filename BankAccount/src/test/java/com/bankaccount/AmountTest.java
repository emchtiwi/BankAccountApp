package com.bankaccount;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.bankaccount.metier.AmountMetier;
import com.bankaccount.pojo.Amount;

public class AmountTest {

	@Test(expected = NullPointerException.class)
	public void amount_should_be_not_null() {
		new Amount(null);
	}

	@Test
	public void verify_the_equality_of_two_amounts() {

		/*
		 * Given
		 */
		Amount firstAmount = new Amount(300f);
		Amount secondAmount = new Amount(300f);
		Amount thirdAmount = new Amount(200f);

		/**
		 * Then
		 */
		assertThat(firstAmount).isEqualTo(secondAmount).isNotEqualTo(thirdAmount);
	}

	@Test
	public void should_initialise_an_amount() {

		/*
		 * Given
		 */
		Amount amount = AmountMetier.toAmount(10f);

		/**
		 * Then
		 */
		assertThat(new Amount(10f)).isEqualTo(amount);
	}

	@Test()
	public void adding_an_amount_to_another_should_return_the_sum_of_both() {

		/**
		 * Given
		 */
		Amount balance = new Amount(300f);
		Amount addedAmount = new Amount(100f);

		/**
		 * When
		 */
		Amount amount = AmountMetier.add(balance, addedAmount);

		/**
		 * Then
		 */
		assertThat(amount).isEqualTo(new Amount(400f));
	}

	@Test(expected = IllegalArgumentException.class)
	public void current_amount_should_be_superior_or_equal_to_subtracted_amount() {

		/**
		 * Given
		 */
		Amount balance = new Amount(100f);
		Amount subtractedAmount = new Amount(300f);

		/**
		 * When
		 */
		AmountMetier.subtract(balance, subtractedAmount);
	}

	@Test()
	public void should_return_the_negative_value() {

		/**
		 * Given
		 */
		Amount balance = new Amount(300f);
		Amount subtractedAmount = new Amount(100f);

		/**
		 * When
		 */
		Amount amount = AmountMetier.subtract(balance, subtractedAmount);

		/**
		 * Then
		 */
		assertThat(amount).isEqualTo(new Amount(-100f));

	}

}
