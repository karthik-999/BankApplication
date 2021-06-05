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

import com.bankapplication.entities.Account;
import com.bankapplication.entities.Beneficiery;
import com.bankapplication.entities.User;
import com.bankapplication.repositories.AccountRepository;
import com.bankapplication.requests.AccountDetailsDTO;
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

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetAccount() {
		Account account = new Account();
		account.setAccountId(1L);
		account.setAccountNumber("1AID1ZG");
		account.setBalance(999L);
		List<Beneficiery> beneficieryAccounts = new ArrayList<>();
		Beneficiery beneficieryaccount = new Beneficiery();
		beneficieryaccount.setBeneficieryId(3L);
//		beneficieryaccount.setBeneficieryAccountNumber(account);
		beneficieryaccount.setBeneficieryNumber("1Z1GZAG3");
		beneficieryAccounts.add(beneficieryaccount);
		account.setBeneficiaryAccounts(beneficieryAccounts);
		Optional<Account> optionalAccount = Optional.of(account);
		when(accountRepository.findByAccountId(Mockito.any(Long.class))).thenReturn(optionalAccount);

		AccountDetailsResponseDTO mockedAccount = accountService.getAccount(1L);
		assertNotNull(mockedAccount);
		assertEquals("1AID1ZG", mockedAccount.getAccountNumber());
	}

	@Test
	void testGetByAccountLong() {
		Account account = new Account();
		account.setAccountId(1L);
		account.setAccountNumber("1AID1ZG");
		account.setBalance(999L);
		List<Beneficiery> beneficieryAccounts = new ArrayList<>();
		Beneficiery beneficieryaccount = new Beneficiery();
		beneficieryaccount.setBeneficieryId(3L);
//		beneficieryaccount.setBeneficieryAccountNumber(account);
		beneficieryaccount.setBeneficieryNumber("1Z1GZAG3");
		beneficieryAccounts.add(beneficieryaccount);
		account.setBeneficiaryAccounts(beneficieryAccounts);
		Optional<Account> optionalAccount = Optional.of(account);
		when(accountRepository.findById(Mockito.any(Long.class))).thenReturn(optionalAccount);

		Account mockedAccount = accountService.getByAccount(1L);
		assertNotNull(mockedAccount);
		assertEquals("1AID1ZG", mockedAccount.getAccountNumber());
	}

	@Test
	void testSaveAccountAccountDetailsDTO() {
		Account account = new Account();
		account.setAccountId(1L);
		account.setAccountNumber("1AID1ZG");
		account.setBalance(999L);
		List<Beneficiery> beneficieryAccounts = new ArrayList<>();
		Beneficiery beneficieryaccount = new Beneficiery();
		beneficieryaccount.setBeneficieryId(3L);
//		beneficieryaccount.setBeneficieryAccountNumber(account);
		beneficieryaccount.setBeneficieryNumber("1Z1GZAG3");
		beneficieryAccounts.add(beneficieryaccount);
		account.setBeneficiaryAccounts(beneficieryAccounts);
//		Optional<Account> optionalAccount = Optional.of(account);

		User user = new User();
		user.setUserId(1L);
		user.setPassword("admin");
		user.setUsername("admin");
		List<Account> accounts = new ArrayList<>();
		user.setUserAccounts(accounts);
		account.setUser(user);

		when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account);

		AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
		accountDetailsDTO.setAccountId(1L);
		accountDetailsDTO.setAccountNumber("1AID1ZG");
		accountDetailsDTO.setBalance(999L);
		List<Beneficiery> beneficieryAccounts1 = new ArrayList<>();
		Beneficiery beneficieryaccount1 = new Beneficiery();
		beneficieryaccount1.setBeneficieryId(3L);
//		beneficieryaccount1.setBeneficieryAccountNumber(account);
		beneficieryaccount1.setBeneficieryNumber("1Z1GZAG3");
		beneficieryAccounts1.add(beneficieryaccount1);
		accountDetailsDTO.setBeneficiaryAccounts(beneficieryAccounts1);

		accountDetailsDTO.setBalance(999L);
		accountDetailsDTO.setUser(user);

		AccountDetailsResponseDTO mockedAccount = accountService.saveAccount(accountDetailsDTO);
		assertNotNull(mockedAccount);
//	assertEquals("1AID1ZG", mockedAccount.getAccountNumber());	
	}

	@Test
	void testGetAllAccounts() {

		Account account = new Account();
		account.setAccountId(1L);
		account.setAccountNumber("1AID1ZG");
		account.setBalance(999L);
		List<Beneficiery> beneficieryAccounts = new ArrayList<>();
		Beneficiery beneficieryaccount = new Beneficiery();
		beneficieryaccount.setBeneficieryId(3L);
//		beneficieryaccount.setBeneficieryAccountNumber(account);
		beneficieryaccount.setBeneficieryNumber("1Z1GZAG3");
		beneficieryAccounts.add(beneficieryaccount);
		account.setBeneficiaryAccounts(beneficieryAccounts);
		User user = new User();
		user.setUserId(1L);
		user.setPassword("admin");
		user.setUsername("admin");
		List<Account> accounts = new ArrayList<>();
		user.setUserAccounts(accounts);
		account.setUser(user);

		accounts.add(account);
		when(accountRepository.findAll()).thenReturn(accounts);

		List<AccountDetailsResponseDTO> mockedAccounts = accountService.getAllAccounts();
		assertNotNull(mockedAccounts);

	}

	@Test
	void testDeleteAccount() {
		Account account = new Account();
		account.setAccountId(1L);
		account.setAccountNumber("1AID1ZG");
		account.setBalance(999L);
		List<Beneficiery> beneficieryAccounts = new ArrayList<>();
		Beneficiery beneficieryaccount = new Beneficiery();
		beneficieryaccount.setBeneficieryId(3L);
		beneficieryaccount.setBeneficieryNumber("1Z1GZAG3");
		beneficieryAccounts.add(beneficieryaccount);
		account.setBeneficiaryAccounts(beneficieryAccounts);
		User user = new User();
		user.setUserId(1L);
		user.setPassword("admin");
		user.setUsername("admin");
		List<Account> accounts = new ArrayList<>();
		user.setUserAccounts(accounts);
		account.setUser(user);
		accounts.add(account);
		Optional<Account> optionalAccount = Optional.of(account);

		when(accountRepository.findByAccountId(Mockito.any(Long.class))).thenReturn(optionalAccount);

		accountService.deleteAccount(1L);

	}

	@Test
	void testUpdateAccount() {
		Account account = new Account();
		account.setAccountId(2L);
		account.setAccountNumber("1AID1ZG");
		account.setBalance(999L);
		List<Beneficiery> beneficieryAccounts = new ArrayList<>();
		Beneficiery beneficieryaccount = new Beneficiery();
		beneficieryaccount.setBeneficieryId(3L);
//		beneficieryaccount.setBeneficieryAccountNumber(account);
		beneficieryaccount.setBeneficieryNumber("1Z1GZAG3");
		beneficieryAccounts.add(beneficieryaccount);
		account.setBeneficiaryAccounts(beneficieryAccounts);
//		Optional<Account> optionalAccount = Optional.of(account);

		User user = new User();
		user.setUserId(1L);
		user.setPassword("admin");
		user.setUsername("admin");
		List<Account> accounts = new ArrayList<>();
		user.setUserAccounts(accounts);
		account.setUser(user);
		when(accountService1.getByAccount(Mockito.any(Long.class))).thenReturn(account);
		when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account);

		AddBeneficiaryDetailsDTO accountDetailsDTO = new AddBeneficiaryDetailsDTO();
		accountDetailsDTO.setAccountId(2L);
		accountDetailsDTO.setAccountNumber("1AID1ZG");
		accountDetailsDTO.setBalance(999L);
		List<Beneficiery> beneficieryAccounts1 = new ArrayList<>();
		Beneficiery beneficieryaccount1 = new Beneficiery();
		beneficieryaccount1.setBeneficieryId(3L);
//		beneficieryaccount1.setBeneficieryAccountNumber(account);
		beneficieryaccount1.setBeneficieryNumber("1Z1GZAG3");
		beneficieryAccounts1.add(beneficieryaccount1);
		accountDetailsDTO.setBeneficiaryAccounts(beneficieryAccounts1);
		accountDetailsDTO.setBalance(999L);

		BeneficiaryDetailsResponseDTO mockedAccount = accountService.updateAccount(accountDetailsDTO);
		assertNotNull(mockedAccount);
	}
	/*
	 * @Test void testSaveAccountAccount() { fail("Not yet implemented"); }
	 * 
	 * @Test void testUpdateAccount() { fail("Not yet implemented"); }
	 * 
	 * @Test void testGetByAccountString() { fail("Not yet implemented"); }
	 */

}
