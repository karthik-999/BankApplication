package com.bankapplication.responses;

import java.util.ArrayList;
import java.util.List;

import com.bankapplication.entities.Account;

public class UserDetailsResponseDTO {

	private Long userId;

	private String username;

	private String password;

	private List<Account> userAccounts = new ArrayList<>();

	private String email;
	
	private String phoneNumber;

	public UserDetailsResponseDTO() {
		super();
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Account> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(List<Account> userAccounts) {
		this.userAccounts = userAccounts;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
}
