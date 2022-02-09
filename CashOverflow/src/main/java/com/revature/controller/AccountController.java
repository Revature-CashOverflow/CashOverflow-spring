package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.revature.dao.AccountRepo;

@Controller
public class AccountController {
	
	private AccountRepo accRepo;
	
	@Autowired
	public AccountController(AccountRepo accRepo) {
		this.accRepo = accRepo;
	}

}
