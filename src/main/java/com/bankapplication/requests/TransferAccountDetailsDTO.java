package com.bankapplication.requests;

import lombok.Data;

@Data
public class TransferAccountDetailsDTO {

	private Integer amount;
	private String beneficieryAccountNumber;
	private String userAccountNumber;

	public TransferAccountDetailsDTO() {
		super();
	}

}
