package com.bankapplication.requests;

import java.util.ArrayList;
import java.util.List;

import com.bankapplication.entities.Account;
import com.bankapplication.entities.Beneficiery;
import com.bankapplication.entities.User;

public class AddBeneficiaryDetailsDTO {

	private Long accountId;

	private String accountNumber;

	private Long balance;

	private List<Beneficiery> beneficiaryAccounts = new ArrayList<>();

	public AddBeneficiaryDetailsDTO() {
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

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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

}
