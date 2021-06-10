package com.bankapplication.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.bankapplication.entities.Account;
import com.bankapplication.entities.Beneficiery;
import com.bankapplication.entities.User;
import com.bankapplication.repositories.AccountRepository;
import com.bankapplication.requests.AddBeneficiaryDetailsDTO;
import com.bankapplication.responses.AccountDetailsResponseDTO;
import com.bankapplication.responses.BeneficiaryDetailsResponseDTO;
import com.bankapplication.services.interfaces.IAccountService;

class AccountServiceImplTest {

	@InjectMocks
	IAccountService accountService = new AccountServiceImpl();

	@Mock
	IAccountService accountService1 = new AccountServiceImpl();

	@Mock
	AccountRepository accountRepository;

	private Account account;
	private Optional<Account> optionalAccount;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		// creating account Object
		account = new Account();
		account.setAccountId(1L);
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
		account.setUser(user);
		account.setUser(user);

		optionalAccount = Optional.of(account);

	}

	@Test
	void testGetAccount() {
		when(accountRepository.findByAccountId(Mockito.any(Long.class))).thenReturn(optionalAccount);

		AccountDetailsResponseDTO mockedAccount = accountService.getAccount(1L);
		assertNotNull(mockedAccount);
		assertEquals("1AID1ZG", mockedAccount.getAccountNumber());
	}

	@Test
	void testGetByAccountLong() {
		when(accountRepository.findById(Mockito.any(Long.class))).thenReturn(optionalAccount);

		Account mockedAccount = accountService.getByAccount(1L);
		assertNotNull(mockedAccount);
		assertEquals("1AID1ZG", mockedAccount.getAccountNumber());
	}

	/*
	 * @Test void testGetAllAccounts() {
	 * 
	 * List<Account> accounts = new ArrayList<>();
	 * 
	 * accounts.add(account);
	 * when(accountRepository.findAll()).thenReturn(accounts); int page = 0; int
	 * size = 2; Pageable pageable = PageRequest.of(page, size,
	 * Sort.by("accountId").ascending()); List<AccountDetailsResponseDTO>
	 * mockedAccounts = accountService.getAllAccounts(pageable);
	 * assertNotNull(mockedAccounts);
	 * 
	 * }
	 */
	
	
	@Test
	void testDeleteAccount() {
		when(accountRepository.findByAccountId(Mockito.any(Long.class))).thenReturn(optionalAccount);
		accountService.deleteAccount(1L);
	}

	@Test
	void testUpdateAccount() {

		when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account);
		when(accountRepository.findById(Mockito.any(Long.class))).thenReturn(optionalAccount);
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
		BeneficiaryDetailsResponseDTO mockedAccount = accountService.updateAccount(accountDetailsDTO);
		assertNotNull(mockedAccount);
	}

}
