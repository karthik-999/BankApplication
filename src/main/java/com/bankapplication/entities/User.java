package com.bankapplication.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="user")
public class User implements Serializable {

	private static final long serialVersionUID = 4477385203237884894L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long userId;

	@Column
	private String password;

	@Column
	private String username;

	@Column(name="Email_ID")
	private String email;
	
	@Column(name="PhoneNumber")
	private String phoneNumber;

	
	@OneToMany(cascade = CascadeType.ALL ,orphanRemoval = true)
	@JoinColumn(name = "userId",referencedColumnName = "userId")
//	@JsonIgnore
	private List<Account> userAccounts;

	public User() {

	}

}
