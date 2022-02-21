package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dto.TransactionDto;
import com.revature.model.Transaction;
import com.revature.service.TransactionService;

@CrossOrigin(origins = { "http://localhost:4200", "http://d3nlmo2v0fs5mq.cloudfront.net" })
@RestController
public class IncomeExpenseController {

	private TransactionService tranServ;

	@Autowired
	public IncomeExpenseController(TransactionService tranServ) {
		this.tranServ = tranServ;
	}

	@PostMapping("/transaction")
	public void addTransaction(@RequestBody TransactionDto dto) {
		if (dto.getAccountId() == 0 || dto.getAmount() == null || dto.getDescription() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing transaction info");
		} else if (dto.getAmount() < 0) {
			throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Cant have have negative transaction");
		} 

		tranServ.addTransaction(dto);
	}
	
	@GetMapping("/getTransactions/{bkId}")
	public List<Transaction> getTransactions(@PathVariable Integer bkId) {
		return tranServ.getTransactions(bkId);
	}

}
