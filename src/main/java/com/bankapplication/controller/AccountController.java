package com.bankapplication.controller;

import com.bankapplication.requests.AddBeneficiaryDetailsDTO;
import com.bankapplication.requests.TransferAccountDetailsDTO;
import com.bankapplication.responses.AccountDetailsResponseDTO;
import com.bankapplication.responses.BeneficiaryDetailsResponseDTO;
import com.bankapplication.responses.ResponseMessage;
import com.bankapplication.services.interfaces.IAccountService;
import com.bankapplication.services.interfaces.ITransactionService;
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

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	public IAccountService accountService;

	@GetMapping("/all/{page}/{size}")
	public ResponseEntity<List<AccountDetailsResponseDTO>> getAccounts(
			@PathVariable(value = "page", required = true) int page,
			@PathVariable(value = "size", required = true) int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("accountId").ascending());
		List<AccountDetailsResponseDTO> accounts = accountService.getAllAccounts(pageable);
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@GetMapping("/get/{accountId}")
	public ResponseEntity<AccountDetailsResponseDTO> getAccount(@PathVariable("accountId") Long accountId) {
		var account = accountService.getAccount(accountId);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{accountId}")
	public ResponseEntity<ResponseMessage> deleteAccount(@PathVariable Long accountId) {
		return new ResponseEntity<>(accountService.deleteAccount(accountId), HttpStatus.OK);
	}

	// add beneficiary
	@PutMapping("/beneficiary/add")
	public ResponseEntity<BeneficiaryDetailsResponseDTO> updateAccount(
			@RequestBody AddBeneficiaryDetailsDTO accountDetails) {
		var accountDetailsResponse = accountService.updateAccount(accountDetails);
		return new ResponseEntity<>(accountDetailsResponse, HttpStatus.OK);
	}

	// transfer amount to beneficiary Account
	@PostMapping("/fundTransfer")
	public ResponseEntity<ResponseMessage> accountTransfer(
			@Valid @RequestBody TransferAccountDetailsDTO transferAccountDetails) {
		return new ResponseEntity<>(accountService.fundTransfer(transferAccountDetails), HttpStatus.OK);
	}

}
