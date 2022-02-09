package com.revature.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.revature.dao.UserAccountRepo;
import com.revature.model.AccountType;
import com.revature.model.BankAccount;
import com.revature.model.UserAccount;
import com.revature.service.BankAccountService;

@Controller
public class AccountController {


	private BankAccountService bankAccServ;
	private UserAccountRepo temp;
	
	@Autowired
	public AccountController(BankAccountService bankAccServ,  UserAccountRepo temp) {
		this.bankAccServ = bankAccServ;
		this.temp = temp;
	}
	
	/**
	 * @param newAccount
	 * @apiNote json params:
	 * 			name,
	 * 			description,
	 * 			accountTypeId,
	 * 			"user": jwt
	 *
	 * @return BankAccount
	 * 
	 * @author Parker Mace, Henry Harvil, Andre Long
	 */
	@PostMapping("/api/account/createAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody BankAccount createAccount(@RequestBody BankAccount newAccount) {
		/* TODO: we will need to generate a user object using a jwt 
		 * instead of passing in the object as a part of the json.
		 * this means we will have to set `"user": UserAccount` in the
		 * request body within this method
		 */
		
		// here we will be using a jwt to assign Account.user to a com.revature.model.User object
		
		return bankAccServ.createAccount(newAccount);
	}

}
