package com.bankapplication.requests;

import lombok.Data;

@Data
public class UserDetailsDTO {

	private String username;

	private String password;

	private String email;

	private String phoneNumber;

	public UserDetailsDTO() {
		super();
	}

}
