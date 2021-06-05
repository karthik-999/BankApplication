package com.bankapplication.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name="account")
@JsonIgnoreProperties(value = { "user", "beneficiaryAccounts", "handler",
		"hibernateLazyInitializer" }, allowSetters = true)
public class Account implements Serializable {

	private static final long serialVersionUID = -9197902894043148898L;

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;

	@Column
	private String accountNumber;

	@Column
	private Long balance;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "userAccount", referencedColumnName = "accountId")
//	@JsonIgnore
	private List<Beneficiery> beneficiaryAccounts = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	@JsonIgnore
	private User user;

//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "FromAccount", referencedColumnName = "accountId")
////	@JsonIgnore
//	private List<Transaction> trasactions = new ArrayList<>();

	public Account() {
		super();
	}


}
