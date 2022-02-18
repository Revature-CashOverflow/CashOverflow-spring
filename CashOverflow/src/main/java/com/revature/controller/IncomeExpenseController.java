package com.revature.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dto.TransactionDto;
import com.revature.model.Transaction;
import com.revature.service.BankAccountService;
import com.revature.service.TransactionService;
import com.revature.service.UserAccountService;

@CrossOrigin(origins = { "http://localhost:4200", "http://d3nlmo2v0fs5mq.cloudfront.net" })
@RestController
public class IncomeExpenseController {
	
	private TransactionService tranServ;
	private UserAccountService userServ;
	private BankAccountService bankServ;
	private ModelMapper mapper;
	
	@Autowired
	public IncomeExpenseController(TransactionService tranServ, UserAccountService userServ, BankAccountService bankServ, ModelMapper mapper) {
		this.bankServ = bankServ;
		this.userServ = userServ;
		this.tranServ = tranServ;
		this.mapper = mapper;
	}
	
	private Transaction convertToEntity(TransactionDto dto) {
		return mapper.map(dto, Transaction.class);
	}
	
	@PostMapping("/transaction")
	public void addTransaction(Authentication auth, @RequestBody TransactionDto dto) {
		if (dto.getId() == 0 || dto.getTxType() == 0 || dto.getAmount() == null || dto.getName() == null
				|| dto.getDescription() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing transaction info");
		}
		if (dto.getTxType() == 1) {
			dto.setAmount(-1 * dto.getAmount());
		}
		Transaction transaction = convertToEntity(dto);
		tranServ.addTransaction(transaction);
		
		
		
		
		
	}
	
//	@GetMapping("/getAccounts")
//	public @ResponseBody List<BankAccount> getAccounts(Authentication auth) {
//		return bankServ.getBankAccounts(userServ.getUserFromUsername(auth.getName()).getId());
//		
//	}

}
