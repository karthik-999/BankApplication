package com.bankapplication.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bankapplication.entities.Account;
import com.bankapplication.entities.Beneficiery;
import com.bankapplication.requests.AccountDetailsDTO;
import com.bankapplication.responses.AccountDetailsResponseDTO;


// writing copy-utility classes because BeanUtils and ModelMapper classes has issues when copying dto to entity classes..
@Component
public class AccountUtilities {

	
	public AccountDetailsResponseDTO copyPropertiesFromEntityToDTO(Account account) {
		AccountDetailsResponseDTO accountDetailsResponseDTO = new AccountDetailsResponseDTO();
		accountDetailsResponseDTO.setAccountId(account.getAccountId());
		accountDetailsResponseDTO.setAccountNumber(account.getAccountNumber());
		accountDetailsResponseDTO.setBalance(account.getBalance());
		accountDetailsResponseDTO.setUser(account.getUser());
		List<Beneficiery> beneficieryAccounts = new ArrayList<>();
		for(Beneficiery beneficieryAccount: account.getBeneficiaryAccounts()) {
			Beneficiery beneficieryAccountResponse = new Beneficiery();
			//beneficieryAccountResponse.setBeneficieryAccountNumber(beneficieryAccount.getBeneficieryAccountNumber());
			beneficieryAccountResponse.setBeneficieryId(beneficieryAccount.getBeneficieryId());
			beneficieryAccountResponse.setBeneficieryNumber(beneficieryAccount.getBeneficieryNumber());
			beneficieryAccounts.add(beneficieryAccountResponse);
		}
		accountDetailsResponseDTO.setBeneficiaryAccounts(beneficieryAccounts);
		return accountDetailsResponseDTO;
	}
	
	public Account copyPropertiesFromDTOToEntity(AccountDetailsResponseDTO accountDetailsResponseDTO1,Account account) {
		account.setAccountId(accountDetailsResponseDTO1.getAccountId());
		account.setAccountNumber(accountDetailsResponseDTO1.getAccountNumber());
		account.setBalance(accountDetailsResponseDTO1.getBalance());
		account.setUser(accountDetailsResponseDTO1.getUser());
		List<Beneficiery> beneficieryAccounts = new ArrayList<>();
		for(Beneficiery beneficieryAccount: accountDetailsResponseDTO1.getBeneficiaryAccounts()) {
			Beneficiery beneficieryAccountResponse = new Beneficiery();
			//beneficieryAccountResponse.setBeneficieryAccountNumber(beneficieryAccount.getBeneficieryAccountNumber());
			beneficieryAccountResponse.setBeneficieryId(beneficieryAccount.getBeneficieryId());
			beneficieryAccountResponse.setBeneficieryNumber(beneficieryAccount.getBeneficieryNumber());
			beneficieryAccounts.add(beneficieryAccountResponse);
		}
		account.setBeneficiaryAccounts(beneficieryAccounts);
		return account;
	}
	
	public Account copyPropertiesFromDTOToEntity(AccountDetailsDTO accountDetailsDTO,Account accountDetailsResponseDTO) {
		accountDetailsResponseDTO.setAccountId(accountDetailsDTO.getAccountId());
		accountDetailsResponseDTO.setAccountNumber(accountDetailsDTO.getAccountNumber());
		accountDetailsResponseDTO.setBalance(accountDetailsDTO.getBalance());
		accountDetailsResponseDTO.setUser(accountDetailsDTO.getUser());
		List<Beneficiery> beneficieryAccounts = new ArrayList<>();
		if(accountDetailsDTO.getBeneficiaryAccounts() != null) {
			for(Beneficiery beneficieryAccount: accountDetailsDTO.getBeneficiaryAccounts()) {
				Beneficiery beneficieryAccountResponse = new Beneficiery();
				//beneficieryAccountResponse.setBeneficieryAccountNumber(beneficieryAccount.getBeneficieryAccountNumber());
				beneficieryAccountResponse.setBeneficieryId(beneficieryAccount.getBeneficieryId());
				beneficieryAccountResponse.setBeneficieryNumber(beneficieryAccount.getBeneficieryNumber());
				beneficieryAccounts.add(beneficieryAccountResponse);
			}
			
		}
		accountDetailsResponseDTO.setBeneficiaryAccounts(beneficieryAccounts);
		return accountDetailsResponseDTO;
	}

	
}
