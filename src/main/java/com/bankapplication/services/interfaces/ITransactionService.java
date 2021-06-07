package com.bankapplication.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.bankapplication.entities.Transaction;
import com.bankapplication.responses.TransactionDetailsResponseDTO;

public interface ITransactionService {

	TransactionDetailsResponseDTO getTransaction(String transactionID);

	Transaction saveTransaction(Transaction transaction);

	List<TransactionDetailsResponseDTO> getAllTransactions(Pageable pageable);

}
