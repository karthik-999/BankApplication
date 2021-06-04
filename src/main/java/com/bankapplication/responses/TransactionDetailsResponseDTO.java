package com.bankapplication.responses;

import com.bankapplication.entities.Account;

public class TransactionDetailsResponseDTO {

	private Long transactionID;

	private Account senderID;

	private Account receiverID;

	private String transactionNumber;

	private Double amount;

	public TransactionDetailsResponseDTO() {
		super();
	}

	public Long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Long transactionID) {
		this.transactionID = transactionID;
	}

	public Account getSenderID() {
		return senderID;
	}

	public void setSenderID(Account senderID) {
		this.senderID = senderID;
	}

	public Account getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(Account receiverID) {
		this.receiverID = receiverID;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
