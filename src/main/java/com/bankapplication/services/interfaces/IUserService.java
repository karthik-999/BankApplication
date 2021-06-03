package com.bankapplication.services.interfaces;

import java.util.List;

import com.bankapplication.requests.UserDetailsDTO;
import com.bankapplication.responses.AccountDetailsResponseDTO;
import com.bankapplication.responses.UserDetailsResponseDTO;

public interface IUserService {

	public  UserDetailsResponseDTO saveUser(UserDetailsDTO userDetails);

	public List<UserDetailsResponseDTO> getAllUsers();

}