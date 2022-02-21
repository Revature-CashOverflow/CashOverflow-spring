package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dto.TransactionDto;
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
	public void addTransaction(Authentication auth, @RequestBody TransactionDto dto) {
		if (dto.getAmount() < 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cant have have negative transaction");
		}
		if (dto.getAccountId() == 0 || dto.getAmount() == null || dto.getDescription() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing transaction info");
		}

		tranServ.addTransaction(dto);
	}

}
