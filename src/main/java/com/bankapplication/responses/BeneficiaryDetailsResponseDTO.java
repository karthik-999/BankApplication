package com.bankapplication.responses;

import java.util.ArrayList;
import java.util.List;

import com.bankapplication.entities.Beneficiery;

import lombok.Data;

@Data
public class BeneficiaryDetailsResponseDTO {

	private Long accountId;

	private String accountNumber;

	private Long balance;

	private List<Beneficiery> beneficiaryAccounts = new ArrayList<>();

}
