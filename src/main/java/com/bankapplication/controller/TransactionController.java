package com.bankapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapplication.responses.TransactionDetailsResponseDTO;
import com.bankapplication.services.interfaces.ITransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	ITransactionService transactionService;
	
	
	@GetMapping("/all/{page}/{size}")
	public ResponseEntity<List<TransactionDetailsResponseDTO>> getAccounts(@PathVariable int page, @PathVariable int size) {
		Pageable pageable = PageRequest.of(page, size,Sort.by("transactionID").ascending().and(Sort.by("createdTime")));
		List<TransactionDetailsResponseDTO> transactions = transactionService.getAllTransactions(pageable);
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

}
