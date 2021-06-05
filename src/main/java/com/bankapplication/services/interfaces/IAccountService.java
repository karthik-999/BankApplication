package com.bankapplication.services.interfaces;

import java.util.List;

import javax.validation.Valid;

import com.bankapplication.entities.Account;
import com.bankapplication.requests.AccountDetailsDTO;
import com.bankapplication.requests.AddBeneficiaryDetailsDTO;
import com.bankapplication.requests.TransferAccountDetailsDTO;
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

	void initiateAmountTransfer(@Valid TransferAccountDetailsDTO requestDetails, Account userAccount,
			Account beneficieryAccount);

	void postAmountTransfer(@Valid TransferAccountDetailsDTO requestDetails, Account userAccount,
			Account beneficieryAccount);

	void initiateTransfer(@Valid TransferAccountDetailsDTO transferAccountDetailsDTO, Account userAccount,
			Account beneficieryAccount);

	boolean isValidRequest(@Valid TransferAccountDetailsDTO transferAccountDetailsDTO);

}