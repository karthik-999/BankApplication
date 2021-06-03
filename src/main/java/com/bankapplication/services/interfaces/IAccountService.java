package com.bankapplication.services.interfaces;

import java.util.List;

import com.bankapplication.entities.Account;
import com.bankapplication.requests.AccountDetailsDTO;
import com.bankapplication.requests.AddBeneficiaryDetailsDTO;
import com.bankapplication.responses.AccountDetailsResponseDTO;
import com.bankapplication.responses.BeneficiaryDetailsResponseDTO;

public interface IAccountService {

	Account getByAccount(Long accountId);

	AccountDetailsResponseDTO saveAccount(AccountDetailsDTO accountDTO);

	List<AccountDetailsResponseDTO> getAllAccounts();

	void deleteAccount(Long accountId);

	AccountDetailsResponseDTO getAccount(Long accountId);

	Account saveAccount(Account account);

	BeneficiaryDetailsResponseDTO updateAccount(AddBeneficiaryDetailsDTO accountDetails);

	Account getByAccount(String beneficieryAccountNumber);

}