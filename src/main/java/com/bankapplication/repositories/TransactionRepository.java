package com.bankapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapplication.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}
