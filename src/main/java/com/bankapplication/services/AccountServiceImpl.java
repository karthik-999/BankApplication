package com.bankapplication.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapplication.entities.Account;
import com.bankapplication.entities.Beneficiery;
import com.bankapplication.repositories.AccountRepository;
import com.bankapplication.requests.AccountDetailsDTO;
import com.bankapplication.responses.AccountDetailsResponseDTO;
import com.bankapplication.services.interfaces.IAccountService;
import com.bankapplication.utilities.AccountUtilities;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	AccountUtilities accountUtilities;

	@Override
	public Account getByAccount(@Valid Long accountId) {

		Optional<Account> account = accountRepository.findById(accountId);
		if (account.isPresent())
			return account.get();
		else
			return null;
	}

	@Override
	public AccountDetailsResponseDTO saveAccount(@Valid AccountDetailsDTO accountDTO) {
		Account account = new Account();
		AccountDetailsResponseDTO accountDetailsResponseDTO = new AccountDetailsResponseDTO();
		account = accountUtilities.copyPropertiesFromDTOToEntity(accountDTO, account);
		if (account != null && account.getAccountNumber() == null) {
			account.setAccountNumber(UUID.randomUUID().randomUUID().toString().substring(0, 9));
		}
		if (account.getBeneficiaryAccounts() != null) {
			for (Beneficiery beneficiaryAccount : account.getBeneficiaryAccounts()) {
				if(beneficiaryAccount.getBeneficieryAccountNumber() == null) {
					beneficiaryAccount.setBeneficieryNumber(UUID.randomUUID().toString().substring(9, 19));
				}
			}
		}
		account = accountRepository.save(account);
		accountDetailsResponseDTO = accountUtilities.copyPropertiesFromEntityToDTO(account);
		return accountDetailsResponseDTO;

	}

	@Override
	public List<AccountDetailsResponseDTO> getAllAccounts() {
		List<AccountDetailsResponseDTO> accountResponse = new ArrayList<AccountDetailsResponseDTO>();
		List<Account> accounts = accountRepository.findAll();
		for (Account account : accounts) {
			accountResponse.add(accountUtilities.copyPropertiesFromEntityToDTO(account));
		}
		return accountResponse;
	}

	@Override
	public void deleteAccount(Long accountId) {
		Optional<Account> accountWrapper = accountRepository.findByAccountId(accountId);
		if (accountWrapper.isPresent()) {
			Account account = accountWrapper.get();
			accountRepository.delete(account);
		}

	}

	@Override
	public Account getAccount(Long accountId) {
		Optional<Account> account = accountRepository.findByAccountId(accountId);
		if (account.isPresent()) {
			return account.get();
		}
		return null;
	}

	@Override
	public Account saveAccount(Account account) {
		return accountRepository.save(account);
	}
}
