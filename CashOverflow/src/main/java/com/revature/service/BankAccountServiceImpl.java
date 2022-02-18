package com.revature.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.BankAccountRepo;
import com.revature.model.BankAccount;
import com.revature.model.FundTransfer;
import com.revature.model.UserAccount;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	private BankAccountRepo bankRepo;

	@Autowired
	protected BankAccountServiceImpl(BankAccountRepo bankRepo) {
		this.bankRepo = bankRepo;
	}

	/**
	 * @return BankAccount
	 * 
	 * @author Parker Mace, Henry Harvil, Andre Long
	 */
	@Override
	public BankAccount createAccount(BankAccount newAccount) {
		// here we will be timestamping the acc creation and setting the balance to 0
		newAccount.setCreationDate(Instant.now());
		newAccount.setBalance(0.0);

		BankAccount check = bankRepo.findByUserAndName(newAccount.getUser(), newAccount.getName());
		
		// if this user has an acc with this name already, don't add a new one to the db
		if (check != null || newAccount.getName().equals("") || newAccount.getName().indexOf(" ") == 0)
			return newAccount;
		else
			return bankRepo.save(newAccount);
	}

	/**
	 * @return BankAccount
	 * 
	 * @author Parker Mace
	 */
	public BankAccount getBankAccount(UserAccount user, String name) {
		return bankRepo.findByUserAndName(user, name);
	}

	/**
	 * @return FundTransfer
	 * 
	 * @author Parker mace
	 */
	public List<BankAccount> transferFunds(UserAccount user, FundTransfer fundTransfer) {
		BankAccount account1 = getBankAccount(user, fundTransfer.getTransferFromAccount());
		BankAccount account2 = getBankAccount(user, fundTransfer.getTransferToAccount());

		// we do this to bundle the db calls for both accounts
		List<BankAccount> accounts = Arrays.asList(account1, account2);

		// if a user tries to be cheeky and enter fractional cents, we will round their request
		fundTransfer.setTransferAmount(Math.round(fundTransfer.getTransferAmount() * 100.0) / 100.0);

		// if they can't afford the tx or an acc is null, don't call the db, don't pass go, don't collect $200
		if (account1 == null || account2 == null || account1.getBalance() < fundTransfer.getTransferAmount() || fundTransfer.getTransferAmount() < 0)
			return accounts;

		account1.setBalance(account1.getBalance() - fundTransfer.getTransferAmount());
		account2.setBalance(account2.getBalance() + fundTransfer.getTransferAmount());

		// we do this to bundle the db calls for both accounts
		bankRepo.saveAll(accounts);
		
		// redundant line for testing purposes
		return accounts;
	}

	/**
	 * @return List<BankAccount>
	 * 
	 * @author Parker Mace, Henry Harvil, Andre Long
	 */
	public List<BankAccount> getBankAccounts(int id) {

		return bankRepo.findAllByUserId(id);
	}

}
