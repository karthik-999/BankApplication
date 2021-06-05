package com.bankapplication.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.bankapplication.entities.Beneficiery;
import com.bankapplication.entities.Transaction;
import com.bankapplication.requests.AccountDetailsDTO;
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
	IAccountService accountService;
	
	@Autowired
	ITransactionService transactionService;

	
	@GetMapping("/all")
	public ResponseEntity<List<AccountDetailsResponseDTO>> getAccounts() {
		List<AccountDetailsResponseDTO> accounts = accountService.getAllAccounts();
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
	public ResponseEntity<BeneficiaryDetailsResponseDTO> updateAccount(
			@RequestBody AddBeneficiaryDetailsDTO accountDetails) {
		var accountDetailsResponseDTO = accountService.updateAccount(accountDetails);
		return new ResponseEntity<>(accountDetailsResponseDTO, HttpStatus.OK);
	}

	// transfer amount to beneficiary
	@PostMapping("/fundTransfer")
	public ResponseEntity<ResponseMessage> accountTransfer(
			@Valid @RequestBody TransferAccountDetailsDTO requestDetails) {
		synchronized (this) {

			if (null != requestDetails) {
				// if beneficiaryAccount is in list of beneficiaries then execute transfer..
				var userAccount = accountService.getByAccount(requestDetails.getUserAccountNumber());
				var beneficieryAccount = accountService.getByAccount(requestDetails.getBeneficieryAccountNumber());
				
				
				for (Beneficiery account : userAccount.getBeneficiaryAccounts()) {
					if (account.getBeneficieryNumber().equals(beneficieryAccount.getAccountNumber())) {
						// initiate transfer..
						Long beneficieryBalance = beneficieryAccount.getBalance() + requestDetails.getAmount();
						beneficieryAccount.setBalance(beneficieryBalance);
						Long userBalance = userAccount.getBalance() - requestDetails.getAmount();
						userAccount.setBalance(userBalance);
						accountService.saveAccount(userAccount);
						
						var transaction = new Transaction();
						transaction.setSenderAccount(userAccount);
						transaction.setReceiverAccount(beneficieryAccount);
						transaction.setTransactionNumber(UUID.randomUUID().toString().replace("-", "").substring(12,21));
						transaction.setAmount(requestDetails.getAmount().doubleValue());
						transaction.setCreatedTime(LocalDateTime.now());
						transactionService.saveTransaction(transaction);
						
						return new ResponseEntity<>(
								new ResponseMessage("Succesfully transferred Money to Beneficiery Account"),
								HttpStatus.OK);

					}

				}
			}
			return new ResponseEntity<>(
					new ResponseMessage("Check Request Details - Account should be in Beneficiary to transfer Money"),
					HttpStatus.BAD_REQUEST);

		}

	}

}
