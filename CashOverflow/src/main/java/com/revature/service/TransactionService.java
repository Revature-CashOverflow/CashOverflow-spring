package com.revature.service;

import java.util.List;

import com.revature.model.BankAccount;

public interface TransactionService {
	
	public boolean addIncome();
	
	public boolean addExpense();
	
	public List<BankAccount> getBankAccounts(int accountId);

}
