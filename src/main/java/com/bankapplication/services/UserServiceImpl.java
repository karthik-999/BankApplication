package com.bankapplication.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapplication.entities.Account;
import com.bankapplication.entities.User;
import com.bankapplication.repositories.UserRepository;
import com.bankapplication.requests.UserDetailsDTO;
import com.bankapplication.responses.UserDetailsResponseDTO;
import com.bankapplication.services.interfaces.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	public UserDetailsResponseDTO saveUser(UserDetailsDTO userDetails) {
		User user = new User();
		UserDetailsResponseDTO userDetailsResponseDTO = new UserDetailsResponseDTO();
		BeanUtils.copyProperties(userDetails, user);
		if (userDetails != null && user.getUserAccounts() == null) {
			List<Account> accounts = new ArrayList<>();
			Account account = new Account();
			account.setAccountNumber(UUID.randomUUID().toString().replace("-", "").substring(0, 9));
			account.setBalance(0L);
			accounts.add(account);
			user.setUserAccounts(accounts);
		}
		user = userRepository.save(user);
		BeanUtils.copyProperties(user, userDetailsResponseDTO);
		return userDetailsResponseDTO;
	}

	@Override
	public List<UserDetailsResponseDTO> getAllUsers() {
		List<UserDetailsResponseDTO> userDetailsResponseDTOs = new ArrayList<>();
		List<User> users = userRepository.findAll();
		for (User user : users) {
			UserDetailsResponseDTO userDetailsResponseDTO = new UserDetailsResponseDTO();
			BeanUtils.copyProperties(user, userDetailsResponseDTO);
			userDetailsResponseDTOs.add(userDetailsResponseDTO);
		}
		return userDetailsResponseDTOs;
	}

}
