package com.revature.service;

import com.revature.dto.TransactionDto;
import com.revature.model.BankAccount;

public interface TransactionService {
	
	public void addTransaction(TransactionDto dto);
	
	public void updateBalance(double newBalance, BankAccount acc);


}
