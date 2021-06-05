package com.bankapplication.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

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

	private static final long serialVersionUID = 7570294900213236886L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long transactionID;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FromAccount", referencedColumnName = "accountId")
	private Account senderAccount;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ToAccount", referencedColumnName = "accountId")
	private Account receiverAccount;

	@Column(name = "Transaction_ID")
	private String transactionNumber;

	@Column(name = "TransferredAmount")
	private Double amount;

	@Column(name="createdTime")
	private LocalDateTime createdTime;

	public Transaction() {
		super();
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Account getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(Account receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	public Account getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(Account senderAccount) {
		this.senderAccount = senderAccount;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

}
