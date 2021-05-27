package com.bankapplication.requests;

import java.util.List;

import com.bankapplication.entities.Account;
import com.bankapplication.entities.Beneficiery;
import com.bankapplication.entities.User;

public class AccountDetailsDTO {

	private Long accountId;

	private String accountNumber;
	
	private Long balance;

	private List<Beneficiery> beneficiaryAccounts;

	private Account parentAccount;

	private User user;

	public AccountDetailsDTO() {
		super();
	}

	public Long getAccountId() {
		return accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public Long getBalance() {
		return balance;
	}

	

	public Account getParentAccount() {
		return parentAccount;
	}

	public User getUser() {
		return user;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	
	public void setParentAccount(Account parentAccount) {
		this.parentAccount = parentAccount;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Beneficiery> getBeneficiaryAccounts() {
		return beneficiaryAccounts;
	}

	public void setBeneficiaryAccounts(List<Beneficiery> beneficiaryAccounts) {
		this.beneficiaryAccounts = beneficiaryAccounts;
	}

}
