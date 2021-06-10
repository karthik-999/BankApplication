package com.bankapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapplication.requests.PaymentDetailsRequest;
import com.bankapplication.responses.PaymentDeductionResponseDTO;
import com.bankapplication.services.interfaces.IPaymentService;
import com.bankapplication.services.interfaces.ITransactionService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	public IPaymentService paymentService;

	@Autowired
	public ITransactionService transactionService;

	@PostMapping("/paymentDeduction")
	public ResponseEntity<PaymentDeductionResponseDTO> paymentDeduction(
			@RequestBody PaymentDetailsRequest paymentDetailsRequest) {
		var paymentDeductionResponseDTO = new PaymentDeductionResponseDTO();
		return paymentService.paymentDeduction(paymentDetailsRequest, paymentDeductionResponseDTO);
	}

	
}
