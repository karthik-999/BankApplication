package com.bankapplication.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.bankapplication.entities.Account;
import com.bankapplication.entities.Beneficiery;
import com.bankapplication.entities.User;
import com.bankapplication.requests.AccountDetailsDTO;
import com.bankapplication.requests.AddBeneficiaryDetailsDTO;
import com.bankapplication.requests.TransferAccountDetailsDTO;
import com.bankapplication.responses.AccountDetailsResponseDTO;
import com.bankapplication.responses.BeneficiaryDetailsResponseDTO;
import com.bankapplication.services.interfaces.IAccountService;

class AccountControllerTest {

	@InjectMocks
	AccountController accountController = new AccountController();

	@Mock
	IAccountService accountService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetAccounts() {
		AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
		accountDetailsDTO.setAccountId(2L);
		accountDetailsDTO.setAccountNumber("1ZKIGYG");
		accountDetailsDTO.setBalance(999.0);
		accountDetailsDTO.setUser(new User());
		AccountDetailsResponseDTO accountDetailsResponseDTO = new AccountDetailsResponseDTO();
		accountDetailsResponseDTO.setAccountId(2L);
		accountDetailsResponseDTO.setAccountNumber("1ZKIGYG");
		accountDetailsResponseDTO.setBalance(999L);
		accountDetailsResponseDTO.setUser(new User());
		List<AccountDetailsResponseDTO> accountDetailsResponseDTOs = new ArrayList<>();
		accountDetailsResponseDTOs.add(accountDetailsResponseDTO);
		int page = 1;
		int size = 2;
		when(accountService.getAllAccounts(Mockito.any(Pageable.class))).thenReturn(accountDetailsResponseDTOs);
		ResponseEntity<List<AccountDetailsResponseDTO>> accountDetailsResponseDTOsMock = accountController
				.getAccounts(page, size);

		assertNotNull(accountDetailsResponseDTOsMock);

	}

	@Test
	void testUpdateAccountAddBeneficiaryDetailsDTO() {

		Account account = new Account();
		account.setAccountId(2L);
		account.setAccountNumber("1AID1ZG");
		account.setBalance(999.0);
		List<Beneficiery> beneficieryAccounts = new ArrayList<>();
		Beneficiery beneficieryaccount = new Beneficiery();
		beneficieryaccount.setBeneficieryId(3L);
		beneficieryaccount.setBeneficieryAccountNumber("1Z1GZAG3");
		beneficieryAccounts.add(beneficieryaccount);
		account.setBeneficiaryAccounts(beneficieryAccounts);
		User user = new User();
		user.setUserId(1L);
		user.setPassword("admin");
		user.setUsername("admin");
		List<Account> accounts = new ArrayList<>();
		user.setUserAccounts(accounts);
		account.setUser(user);

		BeneficiaryDetailsResponseDTO beneficiaryDetailsResponseDTO = new BeneficiaryDetailsResponseDTO();
		when(accountService.updateAccount((AddBeneficiaryDetailsDTO) Mockito.any(Object.class)))
				.thenReturn(beneficiaryDetailsResponseDTO);

		AddBeneficiaryDetailsDTO accountDetailsDTO = new AddBeneficiaryDetailsDTO();
		accountDetailsDTO.setAccountId(2L);
		accountDetailsDTO.setAccountNumber("1AID1ZG");
		accountDetailsDTO.setBalance(999L);
		List<Beneficiery> beneficieryAccounts1 = new ArrayList<>();
		Beneficiery beneficieryaccount1 = new Beneficiery();
		beneficieryaccount1.setBeneficieryId(3L);
		beneficieryaccount1.setBeneficieryAccountNumber("1Z1GZAG3");
		beneficieryAccounts1.add(beneficieryaccount1);
		accountDetailsDTO.setBeneficiaryAccounts(beneficieryAccounts1);
		accountDetailsDTO.setBalance(999L);

		ResponseEntity<BeneficiaryDetailsResponseDTO> mockedAccount = accountController
				.updateAccount(accountDetailsDTO);

		assertNotNull(mockedAccount);
	}

	@Test
	void testAccountTransfer() {
		TransferAccountDetailsDTO transferAccountDetailsDTO = new TransferAccountDetailsDTO();
		transferAccountDetailsDTO.setAmount(999.0);
		transferAccountDetailsDTO.setBeneficieryAccountNumber("1ZKAN3G");
		transferAccountDetailsDTO.setUserAccountNumber("1xTIGTAN");

		Account account = new Account();
		account.setAccountId(2L);
		account.setAccountNumber("1xTIGTAN");
		account.setBalance(999.0);
		List<Beneficiery> beneficieryAccounts = new ArrayList<>();
		Beneficiery beneficieryaccount = new Beneficiery();
		beneficieryaccount.setBeneficieryId(3L);
		beneficieryaccount.setBeneficieryAccountNumber("1ZKAN3G");
		beneficieryAccounts.add(beneficieryaccount);
		account.setBeneficiaryAccounts(beneficieryAccounts);

		Account account1 = new Account();
		account1.setAccountId(4L);
		account1.setAccountNumber("1ZKAN3G");
		account1.setBalance(333.0);
		List<Beneficiery> beneficieryAccounts1 = new ArrayList<>();
		Beneficiery beneficieryaccount1 = new Beneficiery();
		beneficieryaccount1.setBeneficieryId(3L);
		beneficieryaccount1.setBeneficieryAccountNumber("1ZKAN37");
		beneficieryAccounts1.add(beneficieryaccount1);
		account1.setBeneficiaryAccounts(beneficieryAccounts1);

		when(accountService.getByAccount("1xTIGTAN")).thenReturn(account);
		when(accountService.getByAccount("1ZKAN3G")).thenReturn(account1);

		when(accountService.saveAccount(Mockito.any(Account.class))).thenReturn(account);

//		ResponseEntity<ResponseMessage> responseMessage = accountController.accountTransfer(transferAccountDetailsDTO);
//		assertNotNull(responseMessage);

	}

}
