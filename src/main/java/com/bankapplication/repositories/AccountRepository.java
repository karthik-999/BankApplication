package com.bankapplication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapplication.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	//jpaRepositories sample
		public Optional<Account> findByAccountId(Long accountId);

		public Account getByAccountNumber(String beneficieryAccountNumber);


}
