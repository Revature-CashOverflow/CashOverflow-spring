package com.revature.service;

import java.time.Instant;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
	public TransactionServiceImpl(TransactionRepo tranRepo, ModelMapper mapper, BankAccountRepo bankRepo) {
		this.bankRepo = bankRepo;
		this.tranRepo = tranRepo;
		this.mapper = mapper;
	}
	
	private Transaction convertToEntity(TransactionDto dto) {
		return mapper.map(dto, Transaction.class);
	}

	@Override
	public void addTransaction(TransactionDto dto) {
		BankAccount acc = bankRepo.getById(dto.getAccountId());
		if (dto.getTxTypeId() == 1) {
			if (dto.getAmount() > acc.getBalance()) {
				throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Insufficient account balance");
			}
			dto.setAmount(-1 * dto.getAmount());
		}	
		Transaction transaction = convertToEntity(dto);
		updateBalance(transaction.getAmount(), acc);
		transaction.setCreationDate(Instant.now());
		tranRepo.save(transaction);
	}

	@Override
	public void updateBalance(double adjustment, BankAccount acc) {
		double newBalance = acc.getBalance() + adjustment;
		acc.setBalance(newBalance);
		bankRepo.save(acc);
	}

}
