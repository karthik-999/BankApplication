package com.bankapplication.responses;

import java.time.LocalDateTime;

import com.bankapplication.entities.Account;

import lombok.Data;

@Data
public class TransactionDetailsResponseDTO {

	private Long transactionID;

	private String transactionNumber;

	private Double amount;
	
	private Account senderAccount;

	private Account receiverAccount;

	private LocalDateTime createdTime;

	public TransactionDetailsResponseDTO() {
		super();
	}

	
}
