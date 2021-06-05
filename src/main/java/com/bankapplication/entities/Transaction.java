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
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="transaction")
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

}
