package com.bankapplication.responses;

import java.util.ArrayList;
import java.util.List;

import com.bankapplication.entities.Beneficiery;
import com.bankapplication.entities.User;

import lombok.Data;

@Data
public class AccountDetailsResponseDTO {

	private Long accountId;

	private String accountNumber;

	private Long balance;

	private List<Beneficiery> beneficiaryAccounts = new ArrayList<>();

	private User user;

	
	public AccountDetailsResponseDTO() {
		super();
	}

}
