package com.revature.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.BankAccountRepo;
import com.revature.model.BankAccount;

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

		return bankRepo.save(newAccount);
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
