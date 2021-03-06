package com.bankapplication.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bankapplication.entities.Account;
import com.bankapplication.entities.Beneficiery;
import com.bankapplication.entities.Transaction;
import com.bankapplication.repositories.AccountRepository;
import com.bankapplication.requests.AddBeneficiaryDetailsDTO;
import com.bankapplication.requests.PaymentDetailsRequest;
import com.bankapplication.requests.TransferAccountDetailsDTO;
import com.bankapplication.responses.AccountDetailsResponseDTO;
import com.bankapplication.responses.BeneficiaryDetailsResponseDTO;
import com.bankapplication.responses.ResponseMessage;
import com.bankapplication.services.interfaces.IAccountService;
import com.bankapplication.services.interfaces.ITransactionService;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	public ITransactionService transactionService;

	@Autowired
	AccountRepository accountRepository;

	@Override
	public Account getByAccount(@Valid Long accountId) {

		Optional<Account> account = accountRepository.findById(accountId);
		if (account.isPresent()) {
			return account.get();
		} else {
			return new Account();
		}
	}

	@Override
	public List<AccountDetailsResponseDTO> getAllAccounts(Pageable pageable) {
		List<AccountDetailsResponseDTO> accountResponse = new ArrayList<>();
		Page<Account> accountsPage = accountRepository.findAll(pageable);
		for (Account account : accountsPage.getContent()) {
			var accountDetailsResponseDTO = new AccountDetailsResponseDTO();
			BeanUtils.copyProperties(account, accountDetailsResponseDTO);
			accountResponse.add(accountDetailsResponseDTO);
		}
		return accountResponse;
	}

	@Override
	public ResponseMessage deleteAccount(Long accountId) {
		Optional<Account> accountOptional = accountRepository.findByAccountId(accountId);
		if (accountOptional.isPresent()) {
			var account = accountOptional.get();
			accountRepository.delete(account);
			return new ResponseMessage("Successfully Deleted");
		}
		return new ResponseMessage("Account doesn't Exists - Enter Valid AccountId");
	}

	@Override
	public AccountDetailsResponseDTO getAccount(Long accountId) {
		var accountDetailsResponseDTO = new AccountDetailsResponseDTO();
		Optional<Account> account = accountRepository.findByAccountId(accountId);
		if (account.isPresent()) {
			BeanUtils.copyProperties(account.get(), accountDetailsResponseDTO);
			return accountDetailsResponseDTO;
		}
		return null;
	}

	@Override
	public Account saveAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public BeneficiaryDetailsResponseDTO updateAccount(AddBeneficiaryDetailsDTO beneficiaryDetails) {

		var account = new Account();
		var accountDB = new Account();

		var beneficiaryDetailsResponseDTO = new BeneficiaryDetailsResponseDTO();
		if (beneficiaryDetails != null && beneficiaryDetails.getAccountId() != null) {
			accountDB = getByAccount(beneficiaryDetails.getAccountId());
		}

		if (beneficiaryDetails != null) {
			BeanUtils.copyProperties(beneficiaryDetails, account);
			account = accountRepository.save(account);
		} else {
			account = accountDB;
		}

		BeanUtils.copyProperties(account, beneficiaryDetailsResponseDTO);
		return beneficiaryDetailsResponseDTO;
	}

	@Override
	public Account getByAccount(String beneficieryAccountNumber) {
		return accountRepository.getByAccountNumber(beneficieryAccountNumber);
	}

	public void postAmountTransfer(TransferAccountDetailsDTO requestDetails, Account userAccount,
			Account beneficieryAccount) {
		var transaction = new Transaction();
		transaction.setSenderAccount(userAccount);
		transaction.setReceiverAccount(beneficieryAccount);
		transaction.setTransactionNumber(UUID.randomUUID().toString().replace("-", "").substring(12, 21));
		transaction.setAmount(requestDetails.getAmount());
		transaction.setCreatedTime(LocalDateTime.now());
		transactionService.saveTransaction(transaction);
	}
	
	public void postAmountTransfer(PaymentDetailsRequest  requestDetails, Account userAccount,
			Account beneficieryAccount) {
		var transaction = new Transaction();
		transaction.setSenderAccount(userAccount);
		transaction.setReceiverAccount(beneficieryAccount);
		transaction.setTransactionNumber(UUID.randomUUID().toString().replace("-", "").substring(12, 21));
		transaction.setAmount(requestDetails.getPrice());
		transaction.setCreatedTime(LocalDateTime.now());
		transactionService.saveTransaction(transaction);
	}

	public void initiateAmountTransfer(TransferAccountDetailsDTO requestDetails, Account userAccount,
			Account beneficieryAccount) {
		var beneficieryBalance = beneficieryAccount.getBalance() + requestDetails.getAmount();
		beneficieryAccount.setBalance(beneficieryBalance);
		var userBalance = userAccount.getBalance() - requestDetails.getAmount();
		userAccount.setBalance(userBalance);
		saveAccount(userAccount);
	}

	public void initiateTransfer(TransferAccountDetailsDTO transferAccountDetailsDTO, Account userAccount,
			Account beneficieryAccount) {
		// if beneficiaryAccount is in list of beneficiaries then execute transfer..
		for (Beneficiery beneficiery : userAccount.getBeneficiaryAccounts()) {
			if (beneficiery.getBeneficieryAccountNumber().equals(beneficieryAccount.getAccountNumber())) {
				initiateAmountTransfer(transferAccountDetailsDTO, userAccount, beneficieryAccount);
				postAmountTransfer(transferAccountDetailsDTO, userAccount, beneficieryAccount);
			}
		}
	}

	public boolean isValidRequest(TransferAccountDetailsDTO transferAccountDetailsDTO) {
		return (null != transferAccountDetailsDTO && transferAccountDetailsDTO.getUserAccountNumber() != null
				&& transferAccountDetailsDTO.getBeneficieryAccountNumber() != null
				&& transferAccountDetailsDTO.getAmount() > 0);
	}

	public synchronized ResponseMessage fundTransfer(TransferAccountDetailsDTO transferAccountDetailsDTO) {

		// validate request
		if (!isValidRequest(transferAccountDetailsDTO)) {
			return new ResponseMessage("Check Request Details");
		}

		var userAccount = getByAccount(transferAccountDetailsDTO.getUserAccountNumber());
		var beneficieryAccount = getByAccount(transferAccountDetailsDTO.getBeneficieryAccountNumber());

		// check Accounts are valid and have balance..
		if (null != userAccount && null != beneficieryAccount) {

			if (userAccount.getBalance() <= 0 && (userAccount.getBalance() < transferAccountDetailsDTO.getAmount())) {
				return new ResponseMessage("Balance Insufficient - Please add Amount");
			}

			// initiate transfer..
			if (userAccount.getBeneficiaryAccounts() != null && !userAccount.getBeneficiaryAccounts().isEmpty()) {
				initiateTransfer(transferAccountDetailsDTO, userAccount, beneficieryAccount);
				return new ResponseMessage("Amount Transferred");
			}
		}
		return new ResponseMessage("Enter Correct Account numbers to process");
	}

}
