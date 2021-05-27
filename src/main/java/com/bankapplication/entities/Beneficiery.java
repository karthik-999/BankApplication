package com.bankapplication.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Beneficiery {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long beneficieryId;

	@Column
	private String beneficieryNumber;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "accountId")
	private Account beneficieryAccountNumber;

	public Long getBeneficieryId() {
		return beneficieryId;
	}

	public void setBeneficieryId(Long beneficieryId) {
		this.beneficieryId = beneficieryId;
	}

	public String getBeneficieryNumber() {
		return beneficieryNumber;
	}

	public void setBeneficieryNumber(String beneficieryNumber) {
		this.beneficieryNumber = beneficieryNumber;
	}

	public Account getBeneficieryAccountNumber() {
		return beneficieryAccountNumber;
	}

	public void setBeneficieryAccountNumber(Account beneficieryAccountNumber) {
		this.beneficieryAccountNumber = beneficieryAccountNumber;
	}

}