package com.revature.service;

import java.time.Instant;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.BankAccountRepo;
import com.revature.dao.TransactionRepo;
import com.revature.dto.TransactionDto;
import com.revature.model.BankAccount;
import com.revature.model.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService {

	TransactionRepo tranRepo;
	BankAccountRepo bankRepo;
	private ModelMapper mapper;

	@Autowired
	public TransactionServiceImpl(TransactionRepo tranRepo, ModelMapper mapper) {
		this.tranRepo = tranRepo;
		this.mapper = mapper;
	}
	
	private Transaction convertToEntity(TransactionDto dto) {
		return mapper.map(dto, Transaction.class);
	}

	@Override
	public void addTransaction(TransactionDto dto) {
		if (dto.getTxType() == 1) {
			dto.setAmount(-1 * dto.getAmount());
		}
		Transaction transaction = convertToEntity(dto);
		updateBalance(transaction.getAmount(), transaction.getAccountId());
		transaction.setCreationDate(Instant.now());
		tranRepo.save(transaction);
	}

	@Override
	public void updateBalance(double adjustment, int accountId) {
		BankAccount acc = bankRepo.getById(accountId);
		double newBalance = acc.getBalance() + adjustment;
		acc.setBalance(newBalance);
		bankRepo.save(acc);
	}

}
