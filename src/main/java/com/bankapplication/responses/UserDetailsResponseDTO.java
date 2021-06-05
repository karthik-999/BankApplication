package com.bankapplication.responses;

import java.util.ArrayList;
import java.util.List;

import com.bankapplication.entities.Account;

import lombok.Data;

@Data
public class UserDetailsResponseDTO {

	private Long userId;

	private String username;

	private String password;

	private String email;
	
	private String phoneNumber;

	private List<Account> userAccounts = new ArrayList<>();
	
	public UserDetailsResponseDTO() {
		super();
	}
	
}
