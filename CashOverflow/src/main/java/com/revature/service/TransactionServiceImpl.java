package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.TransactionRepo;

@Service
public class TransactionServiceImpl implements TransactionService {

	private TransactionRepo txRepo;

	@Autowired
	public void setUserRepo(TransactionRepo txRepo) {
		this.txRepo = txRepo;
	}
}
