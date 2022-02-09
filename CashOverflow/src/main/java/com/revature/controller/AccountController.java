package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.revature.service.BankAccountService;

@Controller
public class AccountController {

	private BankAccountService bankAccServ;
	
	@Autowired
	public AccountController(BankAccountService bankAccServ) {
		this.bankAccServ = bankAccServ;
	}

}
