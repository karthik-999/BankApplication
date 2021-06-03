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
import com.bankapplication.repositories.AccountRepository;
import com.bankapplication.services.interfaces.IAccountService;

class AccountServiceImplTest {

	@InjectMocks
	IAccountService accountService = new AccountServiceImpl();

	@Mock
	AccountRepository accountRepository;

	@BeforeEach
	private void setUp() throws Exception {
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

//		Account mockedAccount = accountService.getAccount(1L);
//		assertNotNull(mockedAccount);
//		assertEquals("1AID1ZG", mockedAccount.getAccountNumber());
	}

}
