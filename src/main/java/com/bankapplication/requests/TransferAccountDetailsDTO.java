package com.bankapplication.requests;

public class TransferAccountDetailsDTO {
	
	private Integer amount;
	private Long beneficiaryAccountId;
	private Long userAccountId;
	
	public TransferAccountDetailsDTO(Long userAccountId,Long beneficiaryAccountId,Integer amount) {
		this.userAccountId = userAccountId;
		this.beneficiaryAccountId = beneficiaryAccountId;
		this.amount = amount;
	}

	public Integer getAmount() {
		return amount;
	}

	public Long getBeneficiaryAccountId() {
		return beneficiaryAccountId;
	}

	public Long getUserAccountId() {
		return userAccountId;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setBeneficiaryAccountId(Long beneficiaryAccountId) {
		this.beneficiaryAccountId = beneficiaryAccountId;
	}

	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}
	
	
}
