package com.bankapplication.requests;

import java.util.ArrayList;
import java.util.List;

import com.bankapplication.entities.Account;
import com.bankapplication.entities.Beneficiery;
import com.bankapplication.entities.User;

import lombok.Data;

@Data
public class AccountDetailsDTO {

	private Long accountId;

	private String accountNumber;
	
	private Double balance;

	private List<Beneficiery> beneficiaryAccounts = new ArrayList<>();

	private Account parentAccount;

	private User user;

	public AccountDetailsDTO() {
		super();
	}

}
