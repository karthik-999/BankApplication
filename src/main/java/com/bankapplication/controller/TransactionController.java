package com.bankapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapplication.responses.TransactionDetailsResponseDTO;
import com.bankapplication.services.interfaces.ITransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	ITransactionService transactionService;
	@GetMapping("/all")
	public ResponseEntity<List<TransactionDetailsResponseDTO>> getAccounts() {
		List<TransactionDetailsResponseDTO> transactions = transactionService.getAllTransactions();
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	
	
}
