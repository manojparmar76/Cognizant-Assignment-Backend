/**
 * 
 */
package com.rabobank.customer.util;

/**
 * @author Manoj Parmar
 *
 */
public class RecordDescription {

	private Long transactionReference;

	private String accountNumber;

	private Double startBalance;

	private Double mutation;

	private String description;

	private Double endBalance;

	private String message;

	/**
	 * @return the transactionReference
	 */
	public Long getTransactionReference() {
		return transactionReference;
	}

	/**
	 * @param transactionReference
	 *            the transactionReference to set
	 */
	public void setTransactionReference(Long transactionReference) {
		this.transactionReference = transactionReference;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber
	 *            the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the startBalance
	 */
	public Double getStartBalance() {
		return startBalance;
	}

	/**
	 * @param startBalance
	 *            the startBalance to set
	 */
	public void setStartBalance(Double startBalance) {
		this.startBalance = startBalance;
	}

	/**
	 * @return the mutation
	 */
	public Double getMutation() {
		return mutation;
	}

	/**
	 * @param mutation
	 *            the mutation to set
	 */
	public void setMutation(Double mutation) {
		this.mutation = mutation;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the endBalance
	 */
	public Double getEndBalance() {
		return endBalance;
	}

	/**
	 * @param endBalance
	 *            the endBalance to set
	 */
	public void setEndBalance(Double endBalance) {
		this.endBalance = endBalance;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
