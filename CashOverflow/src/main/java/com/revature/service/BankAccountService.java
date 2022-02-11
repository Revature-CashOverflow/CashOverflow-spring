package com.revature.service;

import java.util.List;

import com.revature.model.BankAccount;

public interface BankAccountService {

	public BankAccount createAccount(BankAccount newAccount);

	public List<BankAccount> getBankAccounts(int id);

}
