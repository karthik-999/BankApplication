package com.bankapplication.requests;

public class TransferAccountDetailsDTO {
	
	private Integer amount;
	private String beneficieryAccountNumber;
	private String userAccountNumber;
	
	public TransferAccountDetailsDTO(String userAccountId,String beneficiaryAccountId,Integer amount) {
		this.userAccountNumber = userAccountId;
		this.beneficieryAccountNumber = beneficiaryAccountId;
		this.amount = amount;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getBeneficieryAccountNumber() {
		return beneficieryAccountNumber;
	}

	public void setBeneficieryAccountNumber(String beneficieryAccountNumber) {
		this.beneficieryAccountNumber = beneficieryAccountNumber;
	}

	public String getUserAccountNumber() {
		return userAccountNumber;
	}

	public void setUserAccountNumber(String userAccountNumber) {
		this.userAccountNumber = userAccountNumber;
	}


	
	
}
