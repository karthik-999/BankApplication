package com.bankapplication.responses;

import java.util.List;

import com.bankapplication.entities.Beneficiery;
import com.bankapplication.entities.User;

public class AccountDetailsResponseDTO {

	private Long accountId;

	private String accountNumber;

	private Long balance;

	private List<Beneficiery> beneficiaryAccounts;

	private User user;

	public AccountDetailsResponseDTO() {

	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public List<Beneficiery> getBeneficiaryAccounts() {
		return beneficiaryAccounts;
	}

	public void setBeneficiaryAccounts(List<Beneficiery> beneficiaryAccounts) {
		this.beneficiaryAccounts = beneficiaryAccounts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
 	}

	

}
