package com.bankapplication.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "user", "beneficiaryAccounts", "handler",
		"hibernateLazyInitializer" }, allowSetters = true)
public class Account implements Serializable {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;

	@Column
	private String accountNumber;

	@Column
	private Long balance;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "userAccount", referencedColumnName = "accountId")
//	@JsonIgnore
	private List<Beneficiery> beneficiaryAccounts = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
//	@JsonIgnore
	private User user;

	public Long getAccountId() {
		return accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public Long getBalance() {
		return balance;
	}

	public List<Beneficiery> getBeneficiaryAccounts() {
		return beneficiaryAccounts;
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

	public void setBeneficiaryAccounts(List<Beneficiery> beneficiaryAccounts) {
		this.beneficiaryAccounts = beneficiaryAccounts;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
