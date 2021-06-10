package com.bankapplication.requests;

import java.util.ArrayList;
import java.util.List;

import com.bankapplication.entities.Beneficiery;

import lombok.Data;

@Data
public class AddBeneficiaryDetailsDTO {

	private Long accountId;

	private String accountNumber;

	private Double balance;

	private List<Beneficiery> beneficiaryAccounts = new ArrayList<>();

}
