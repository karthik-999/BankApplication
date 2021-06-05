package com.bankapplication.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bankapplication.entities.Transaction;
import com.bankapplication.repositories.TransactionRepository;
import com.bankapplication.requests.TransactionDetailsDTO;
import com.bankapplication.responses.TransactionDetailsResponseDTO;
import com.bankapplication.services.interfaces.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	
	@Override
	public TransactionDetailsResponseDTO saveTransaction(TransactionDetailsDTO transactionDTO) {

		return null;
	}

	@Override
	public List<TransactionDetailsResponseDTO> getAllTransactions(Pageable pageable) {
		var transactionDetailsResponseDTOs = new ArrayList<TransactionDetailsResponseDTO>();
		var transactionDetailsResponseDTO = new TransactionDetailsResponseDTO();
		Page<Transaction>transactions = transactionRepository.findAll(pageable);
		for (Transaction transaction : transactions) {
			BeanUtils.copyProperties(transaction, transactionDetailsResponseDTO);
			transactionDetailsResponseDTOs.add(transactionDetailsResponseDTO);
		}
		return transactionDetailsResponseDTOs;
	}

	@Override
	public TransactionDetailsResponseDTO getTransaction(String transactionID) {
		return null;
	}

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

}
