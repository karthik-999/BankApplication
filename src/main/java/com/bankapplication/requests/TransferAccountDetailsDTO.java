package com.bankapplication.requests;

import lombok.Data;

@Data
public class TransferAccountDetailsDTO {

	private Double amount;
	private String beneficieryAccountNumber;
	private String userAccountNumber;

	public TransferAccountDetailsDTO() {
		super();
	}

}
