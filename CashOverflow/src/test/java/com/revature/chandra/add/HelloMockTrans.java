// this is a test class that can be safely deleted
package com.revature.chandra.add;

import java.time.Instant;

import org.springframework.http.HttpStatus;

import com.revature.dto.TransactionDto;
import com.revature.model.BankAccount;
import com.revature.model.Transaction;
import com.revature.dao.BankAccountRepo;
import com.revature.dao.TransactionRepo;
import org.modelmapper.ModelMapper;


public class HelloMockTrans {
	
	TransactionRepo tranRepo;
	BankAccountRepo bankRepo;
	
	private Transaction convertToEntity(TransactionDto dto) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(dto, Transaction.class);
	}
	

	public double addTransaction(TransactionDto dto) {
		if (dto.getTxType() == 1) {
			dto.setAmount(-1 * dto.getAmount());
		}
		Transaction transaction = convertToEntity(dto);
		transaction.setCreationDate(Instant.now());
		return updateBalance(1.0, 45);  //account #45 is hardwired
		
//		tranRepo.save(transaction);
	}
	
	
	public double updateBalance(double adjustment, int accountId) {
		BankAccount acc = bankRepo.getById(accountId);
		double newBalance = 105 + adjustment;
        return newBalance;
	}
}
