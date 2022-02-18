package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.TransactionRepo;
import com.revature.model.Transaction;


@Service
public class TransactionServiceImpl implements TransactionService {
	
	TransactionRepo tranRepo;
	
	@Autowired
	public TransactionServiceImpl(TransactionRepo tranRepo) {
		this.tranRepo = tranRepo;
	}

	@Override
	public void addTransaction(Transaction transaction) {
		updateBalance(transaction.getAmount());
	}

	@Override
	public void updateBalance(double adjustment) {
	
		
	}
	
	
	
	

}
