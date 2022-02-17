package com.revature.service;

import com.revature.model.Transaction;

public interface TransactionService {
	
	public boolean addTransaction(Transaction transaction);
	
	public void updateBalance(double newBalance);


}
