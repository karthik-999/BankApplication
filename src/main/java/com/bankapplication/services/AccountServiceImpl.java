package com.bankapplication.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapplication.entities.Account;
import com.bankapplication.repositories.AccountRepository;
import com.bankapplication.requests.AccountDetailsDTO;
import com.bankapplication.requests.AddBeneficiaryDetailsDTO;
import com.bankapplication.responses.AccountDetailsResponseDTO;
import com.bankapplication.responses.BeneficiaryDetailsResponseDTO;
import com.bankapplication.services.interfaces.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	AccountRepository accountRepository;

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
		var account = new Account();
		var accountDetailsResponseDTO = new AccountDetailsResponseDTO();
		BeanUtils.copyProperties(accountDTO, account);
			if (account.getAccountNumber() == null) {
				account.setAccountNumber(UUID.randomUUID().toString().substring(0, 9).toUpperCase());
			account = accountRepository.save(account);
		}
		BeanUtils.copyProperties(account, accountDetailsResponseDTO);
		return accountDetailsResponseDTO;

	}

	@Override
	public List<AccountDetailsResponseDTO> getAllAccounts() {
		List<AccountDetailsResponseDTO> accountResponse = new ArrayList<>();
		List<Account> accounts = accountRepository.findAll();
		for (Account account : accounts) {
			var accountDetailsResponseDTO = new AccountDetailsResponseDTO();
			BeanUtils.copyProperties(account, accountDetailsResponseDTO);
			accountResponse.add(accountDetailsResponseDTO);
		}
		return accountResponse;
	}

	@Override
	public void deleteAccount(Long accountId) {
		Optional<Account> accountWrapper = accountRepository.findByAccountId(accountId);
		if (accountWrapper.isPresent()) {
			var account = accountWrapper.get();
			accountRepository.delete(account);
		}

	}

	@Override
	public AccountDetailsResponseDTO getAccount(Long accountId) {
		var accountDetailsResponseDTO = new AccountDetailsResponseDTO();
		Optional<Account> account = accountRepository.findByAccountId(accountId);
		if (account.isPresent()) {
			BeanUtils.copyProperties(account.get(),accountDetailsResponseDTO );
			return accountDetailsResponseDTO;
		}
		return null;
	}

	@Override
	public Account saveAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public BeneficiaryDetailsResponseDTO updateAccount(AddBeneficiaryDetailsDTO beneficiaryDetails) {
		
		var account = new Account();
		var accountDB = new Account();

		var beneficiaryDetailsResponseDTO = new BeneficiaryDetailsResponseDTO();
		if(beneficiaryDetails != null && beneficiaryDetails.getAccountId() != null) {
			 accountDB = getByAccount(beneficiaryDetails.getAccountId());
		}
		if(account.getUser() == null && accountDB != null ) {
			account.setUser(accountDB.getUser());
		}
		if(beneficiaryDetails != null) {
			BeanUtils.copyProperties(beneficiaryDetails, account);
			account = accountRepository.save(account);	
		}else {
			account = accountDB;
		}
		
		BeanUtils.copyProperties(account, beneficiaryDetailsResponseDTO);
		return beneficiaryDetailsResponseDTO;
	}

	@Override
	public Account getByAccount(String beneficieryAccountNumber) {
		return accountRepository.getByAccountNumber(beneficieryAccountNumber);
	}
}
