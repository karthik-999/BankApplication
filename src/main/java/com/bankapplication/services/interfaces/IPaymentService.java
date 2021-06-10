package com.bankapplication.services.interfaces;

import org.springframework.http.ResponseEntity;

import com.bankapplication.requests.PaymentDetailsRequest;
import com.bankapplication.responses.PaymentDeductionResponseDTO;

public interface IPaymentService {

	ResponseEntity<PaymentDeductionResponseDTO> paymentDeduction(PaymentDetailsRequest paymentDetailsRequest,
			PaymentDeductionResponseDTO paymentDeductionResponseDTO);

	
	
}
