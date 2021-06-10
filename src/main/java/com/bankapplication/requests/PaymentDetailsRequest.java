package com.bankapplication.requests;

import lombok.Data;

@Data
public class PaymentDetailsRequest {

	private String accountNumber;
	
	private Double price;

}
