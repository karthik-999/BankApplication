package com.bankapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapplication.requests.UserDetailsDTO;
import com.bankapplication.responses.UserDetailsResponseDTO;
import com.bankapplication.services.interfaces.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	IUserService userService;
	
	@PostMapping("/user/add")
	public ResponseEntity<UserDetailsResponseDTO> addUser(@RequestBody UserDetailsDTO userDetails) {
		var userDetailsResponseDTO = userService.saveUser(userDetails);
		return new ResponseEntity<>(userDetailsResponseDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserDetailsResponseDTO>> getAccounts() {
		List<UserDetailsResponseDTO> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
}
