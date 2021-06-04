package com.bankapplication.services.interfaces;

import java.util.List;

import com.bankapplication.entities.Transaction;
import com.bankapplication.requests.TransactionDetailsDTO;
import com.bankapplication.responses.TransactionDetailsResponseDTO;

public interface ITransactionService {


	
	TransactionDetailsResponseDTO saveTransaction(TransactionDetailsDTO transactionDTO);

	List<TransactionDetailsResponseDTO> getAllTransactions();

	TransactionDetailsResponseDTO getTransaction(String transactionID);

	Transaction saveTransaction(Transaction transaction);

}
