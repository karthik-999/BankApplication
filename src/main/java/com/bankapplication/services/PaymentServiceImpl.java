package com.bankapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankapplication.requests.PaymentDetailsRequest;
import com.bankapplication.responses.PaymentDeductionResponseDTO;
import com.bankapplication.services.interfaces.IAccountService;
import com.bankapplication.services.interfaces.IPaymentService;

@Service
public class PaymentServiceImpl implements IPaymentService {
	
	@Autowired
	IAccountService accountService;
	

	public ResponseEntity<PaymentDeductionResponseDTO> paymentDeduction(PaymentDetailsRequest paymentDetailsRequest,
			PaymentDeductionResponseDTO paymentDeductionResponseDTO) {
		var account = accountService.getByAccount(paymentDetailsRequest.getAccountNumber());

		if (null != account) {

			if (account.getBalance() <= 0 && (account.getBalance() < paymentDetailsRequest.getPrice())) {
				return new ResponseEntity<>(paymentDeductionResponseDTO, HttpStatus.BAD_REQUEST);
			}

			var userBalance = account.getBalance() - paymentDetailsRequest.getPrice();
			account.setBalance(userBalance);
			accountService.saveAccount(account);
		}

		return new ResponseEntity<>(paymentDeductionResponseDTO, HttpStatus.ACCEPTED);
	}
}
