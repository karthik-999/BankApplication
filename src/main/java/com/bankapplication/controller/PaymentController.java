package com.bankapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapplication.requests.PaymentDetailsRequest;
import com.bankapplication.responses.PaymentDeductionResponseDTO;
import com.bankapplication.services.interfaces.IAccountService;
import com.bankapplication.services.interfaces.ITransactionService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	public IAccountService accountService;

	@Autowired
	public ITransactionService transactionService;

	@GetMapping("/paymentDeduction")
	public ResponseEntity<PaymentDeductionResponseDTO> paymentDeduction(
			@RequestBody PaymentDetailsRequest paymentDetailsRequest) {
		var paymentDeductionResponseDTO = new PaymentDeductionResponseDTO();
		return new ResponseEntity<>(paymentDeductionResponseDTO, HttpStatus.ACCEPTED);
	}
}
