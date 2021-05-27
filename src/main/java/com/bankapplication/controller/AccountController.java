package com.bankapplication.controller;

import java.util.List;

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

import com.bankapplication.entities.Account;
import com.bankapplication.entities.Beneficiery;
import com.bankapplication.requests.AccountDetailsDTO;
import com.bankapplication.requests.TransferAccountDetailsDTO;
import com.bankapplication.responses.AccountDetailsResponseDTO;
import com.bankapplication.responses.ResponseMessage;
import com.bankapplication.services.interfaces.IAccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	IAccountService accountService;

	@PostMapping("/add/account")
	public ResponseEntity<AccountDetailsResponseDTO> addAccount(@RequestBody AccountDetailsDTO accountDetails) {
		AccountDetailsResponseDTO accountDetailsResponse = accountService.saveAccount(accountDetails);
		return new ResponseEntity<AccountDetailsResponseDTO>(accountDetailsResponse, HttpStatus.CREATED);
	}

	@GetMapping("/get/accounts")
	public ResponseEntity<List<AccountDetailsResponseDTO>> getAccounts() {

		List<AccountDetailsResponseDTO> accounts = accountService.getAllAccounts();
		return new ResponseEntity<List<AccountDetailsResponseDTO>>(accounts, HttpStatus.OK);
	}

	@GetMapping("/get/account/{accountId}")
	public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
		Account account = accountService.getAccount(accountId);
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

	@PutMapping("/update/account")
	public ResponseEntity<AccountDetailsResponseDTO> updateAccount(@RequestBody AccountDetailsDTO accountDetails) {
		AccountDetailsResponseDTO accountDetailsResponseDTO = accountService.saveAccount(accountDetails);
		return new ResponseEntity<AccountDetailsResponseDTO>(accountDetailsResponseDTO, HttpStatus.OK);

	}

	@DeleteMapping("/delete/account")
	public ResponseEntity<ResponseMessage> deleteAccount(@PathVariable Long accountId) {

		accountService.deleteAccount(accountId);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage("Successfully Deleted"), HttpStatus.OK);
	}

	// Add Beneficiary
	// Update beneficiaries
	// Delete Beneficiary
	// Get Beneficiaries

	// transfer amount to beneficiary
	@PostMapping("/amount/transfer")
	public ResponseEntity<ResponseMessage> accountTransfer(
			@Valid @RequestBody TransferAccountDetailsDTO requestDetails) {
		synchronized (this) {

			if (null != requestDetails) {
				// if beneficiaryAccount is in list of beneficiaries then execute transfer..
				Account userAccount = accountService.getByAccount(requestDetails.getUserAccountId());
				Account beneficieryAccount = accountService.getByAccount(requestDetails.getBeneficiaryAccountId());
				for (Beneficiery account : userAccount.getBeneficiaryAccounts()) {
					if (account.getBeneficieryAccountNumber().equals(beneficieryAccount)) {
						// initiate transfer..
						Long beneficieryBalance = beneficieryAccount.getBalance() + requestDetails.getAmount();
						beneficieryAccount.setBalance(beneficieryBalance);
						Long userBalance = userAccount.getBalance() - requestDetails.getAmount();
						userAccount.setBalance(userBalance);
						accountService.saveAccount(userAccount);
						return new ResponseEntity<>(new ResponseMessage("Succesfully transferred Money to Beneficiery Account"),HttpStatus.OK);

					}

				}
			}
			return new ResponseEntity<>(
					new ResponseMessage("Check Request Details - Account should be Beneficiary to transfer Money"),	HttpStatus.BAD_REQUEST);

		}

	}

}
