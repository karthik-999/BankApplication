package com.bankapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapplication.requests.AddBeneficiaryDetailsDTO;
import com.bankapplication.requests.TransferAccountDetailsDTO;
import com.bankapplication.responses.AccountDetailsResponseDTO;
import com.bankapplication.responses.BeneficiaryDetailsResponseDTO;
import com.bankapplication.responses.ResponseMessage;
import com.bankapplication.services.interfaces.IAccountService;
import com.bankapplication.services.interfaces.ITransactionService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	public IAccountService accountService;
	@Autowired
	public ITransactionService transactionService;

	@GetMapping("/all/{page}/{size}")
	public ResponseEntity<List<AccountDetailsResponseDTO>> getAccounts(@PathVariable int page, @PathVariable int size) {
		Pageable pageable = PageRequest.of(page, size,Sort.by("accountId").ascending());
		List<AccountDetailsResponseDTO> accounts = accountService.getAllAccounts(pageable);
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@GetMapping("/get/{accountId}")
	public ResponseEntity<AccountDetailsResponseDTO> getAccount(@PathVariable Long accountId) {
		var account = accountService.getAccount(accountId);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{accountId}")
	public ResponseEntity<ResponseMessage> deleteAccount(@PathVariable Long accountId) {
		accountService.deleteAccount(accountId);
		return new ResponseEntity<>(new ResponseMessage("Successfully Deleted"), HttpStatus.OK);
	}

	// add beneficiary
	@PutMapping("/beneficiary/add")
	public ResponseEntity<BeneficiaryDetailsResponseDTO> updateAccount(@RequestBody AddBeneficiaryDetailsDTO accountDetails) {
		var accountDetailsResponseDTO = accountService.updateAccount(accountDetails);
		return new ResponseEntity<>(accountDetailsResponseDTO, HttpStatus.OK);
	}

	// transfer amount to beneficiary Account
	@PostMapping("/fundTransfer")
	public ResponseEntity<ResponseMessage> accountTransfer(@Valid @RequestBody TransferAccountDetailsDTO transferAccountDetailsDTO) {

		// validate request
		if (accountService.isValidRequest(transferAccountDetailsDTO)) {
			var userAccount = accountService.getByAccount(transferAccountDetailsDTO.getUserAccountNumber());
			var beneficieryAccount = accountService.getByAccount(transferAccountDetailsDTO.getBeneficieryAccountNumber());

			if (null != userAccount && null != beneficieryAccount) {

				// userAccount is valid and have enough balance
				if (userAccount.getBalance() <= 0 && (userAccount.getBalance() < transferAccountDetailsDTO.getAmount())) {
					return new ResponseEntity<>(new ResponseMessage("Balance Insufficient - Please add Amount"), HttpStatus.OK);
				}

				// initiate transfer..
				if (userAccount.getBeneficiaryAccounts() != null && !userAccount.getBeneficiaryAccounts().isEmpty()) {
					accountService.initiateTransfer(transferAccountDetailsDTO, userAccount, beneficieryAccount);
					return new ResponseEntity<>(new ResponseMessage("Amount Transferred"), HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<>(new ResponseMessage("Check Request Details"), HttpStatus.BAD_REQUEST);
	}

}
