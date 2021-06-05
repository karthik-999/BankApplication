package com.bankapplication.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="beneficiery")
public class Beneficiery implements Serializable {

	private static final long serialVersionUID = 6382746836896590486L;

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long beneficieryId;

	@Column(name="beneficieryNumber")
	private String beneficieryNumber;

	public Beneficiery() {
		super();
	}


}
