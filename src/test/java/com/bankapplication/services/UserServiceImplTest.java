package com.bankapplication.services;

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

import com.bankapplication.entities.Account;
import com.bankapplication.entities.Beneficiery;
import com.bankapplication.entities.User;
import com.bankapplication.repositories.UserRepository;
import com.bankapplication.requests.UserDetailsDTO;
import com.bankapplication.responses.UserDetailsResponseDTO;
import com.bankapplication.services.interfaces.IUserService;

class UserServiceImplTest {

	@InjectMocks
	IUserService userService = new UserServiceImpl();
	
	@Mock
	UserRepository userRepository;
	
	@BeforeEach
	void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
	
	}

	
	
	
	@Test
	void testSaveUser() {
		User user = new User();
		user.setUserId(1L);
		user.setPassword("admin");
		user.setUsername("admin");
		Account account = new Account();
		account.setAccountId(1L);
		account.setAccountNumber("1AID1ZG");
		account.setBalance(999.0);
		List<Beneficiery> beneficieryAccounts = new ArrayList<>();
		Beneficiery beneficieryaccount = new Beneficiery();
		beneficieryaccount.setBeneficieryId(3L);
//		beneficieryaccount.setBeneficieryAccountNumber(account);
		beneficieryaccount.setBeneficieryAccountNumber("1Z1GZAG3");
		beneficieryAccounts.add(beneficieryaccount);
		account.setBeneficiaryAccounts(beneficieryAccounts);
		List<Account> accounts = new ArrayList<>();
		accounts.add(account);
		user.setUserAccounts(accounts);
		when(userRepository.save((User)Mockito.any(Object.class))).thenReturn(user);
		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
		userDetailsDTO.setUsername("admin");
		userDetailsDTO.setPassword("admin");
		UserDetailsResponseDTO userDetailsResponseDTO = userService.saveUser(userDetailsDTO);
		assertNotNull(userDetailsResponseDTO);
//		assertEquals(expected, actual);
	}

	@Test
	void testGetAllUsers() {
		User user = new User();
		user.setUserId(1L);
		user.setPassword("admin");
		user.setUsername("admin");
		Account account = new Account();
		account.setAccountId(1L);
		account.setAccountNumber("1AID1ZG");
		account.setBalance(999.0);
		List<Beneficiery> beneficieryAccounts = new ArrayList<>();
		Beneficiery beneficieryaccount = new Beneficiery();
		beneficieryaccount.setBeneficieryId(3L);
//		beneficieryaccount.setBeneficieryAccountNumber(account);
		beneficieryaccount.setBeneficieryAccountNumber("1Z1GZAG3");
		beneficieryAccounts.add(beneficieryaccount);
		account.setBeneficiaryAccounts(beneficieryAccounts);
		List<Account> accounts = new ArrayList<>();
		accounts.add(account);
		user.setUserAccounts(accounts);
		List<User> users = new ArrayList<>();
		users.add(user);
		when(userRepository.findAll()).thenReturn(users);
		List<UserDetailsResponseDTO> userDetailsResponseDTOs = userService.getAllUsers();
		assertNotNull(userDetailsResponseDTOs);
		
	
	}

}
