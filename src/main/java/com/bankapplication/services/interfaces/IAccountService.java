package com.bankapplication.services.interfaces;

import java.util.List;

import javax.validation.Valid;

import com.bankapplication.entities.Account;
import com.bankapplication.requests.AccountDetailsDTO;
import com.bankapplication.responses.AccountDetailsResponseDTO;

public interface IAccountService {

	Account getByAccount(Long accountId);

	AccountDetailsResponseDTO saveAccount(AccountDetailsDTO accountDTO);

	List<AccountDetailsResponseDTO> getAllAccounts();

	void deleteAccount(Long accountId);

	Account getAccount(Long accountId);

	Account saveAccount(Account account);

}