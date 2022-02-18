package com.revature.service;

import com.revature.dto.TransactionDto;

public interface TransactionService {
	
	public void addTransaction(TransactionDto dto);
	
	public void updateBalance(double newBalance, int accountId);


}
