package com.bankapplication.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction implements Serializable{

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long transactionID;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FromAccount", referencedColumnName = "userId")
	private Account senderID;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ToAccount", referencedColumnName = "userId")
	private Account receiverID;

	@Column(name = "Transaction_ID")
	private String transactionNumber;

	@Column(name = "TransferredAmount")
	private Double amount;

	public Transaction() {
		super();
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Long transactionID) {
		this.transactionID = transactionID;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

}
