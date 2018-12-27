/**
 * 
 */
package com.rabobank.customer.util;

import java.util.ArrayList;

/**
 * @author Manoj Parmar
 *
 */
public class Validate {

	/**
	 * Variable to hold the unique Transaction Reference numbers
	 */
	public static ArrayList<Long> transactionReferenceList = new ArrayList<Long>();

	/**
	 * Method to validate the Transaction Reference number.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isValidTransactionReference(Long value) {
		if (transactionReferenceList.contains(value)) {
			// If variable hold the value it means it's duplicate and return as
			// a false.
			return false;
		} else {
			// Else we added the value in the variable and return as a true.
			transactionReferenceList.add(value);
			return true;
		}
	}

	/**
	 * Method to validate End Balance based on Starting Balance and Mutation
	 * value.
	 * 
	 * @param startBalance
	 * @param mutation
	 * @param endBalance
	 * @return
	 */
	public static boolean isValidEndBalance(Double startBalance, Double mutation, Double endBalance) {
		// Perform calculation for the End Balance.
		Double balance = (startBalance) + (mutation);
		balance = roundValue(balance);

		// Compare the given value and calculated value
		if (Double.compare(balance, endBalance) == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to find maximum 2 digit after decimal.
	 * 
	 * @param value
	 * @return
	 */
	private static Double roundValue(Double value) {
		value = value * 100;
		value = (double) Math.round(value);
		value = value / 100;
		return value;
	}

}
