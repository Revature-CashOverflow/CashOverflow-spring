package com.revature.controller;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.revature.dao.AccountRepo;

@Controller
public class AccountController {
	
	private AccountRepo accRepo;
	
	@Autowired
	public AccountController(AccountRepo accRepo) {
		this.accRepo = accRepo;
=======
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.revature.dao.UserRepo;
import com.revature.model.AccountType;
import com.revature.model.BankAccount;
import com.revature.model.UserAccount;
import com.revature.service.BankAccountService;

@Controller
public class AccountController {


	private BankAccountService bankAccServ;
	private UserRepo temp;
	
	@Autowired
	public AccountController(BankAccountService bankAccServ,  UserRepo temp) {
		this.bankAccServ = bankAccServ;
		this.temp = temp;
	}
	
	/**
	 * 
	 * @param newAccount
	 * @return
	 * 
	 * @author Parker Mace
	 */
	// TODO: we will need to generate a user object using a jwt instead of passing in the object as a part of the json
	@PostMapping("/api/account/createAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody BankAccount createAccount(@RequestBody BankAccount newAccount) {
		
		// here we will be using a jwt to assign Account.user to a com.revature.model.User object
		Instant time = Instant.now();
		AccountType type = new AccountType(1,"checking");
		
		List<UserAccount> userlist = temp.findAll();
		UserAccount user = userlist.get(0);
		
		BankAccount acc = new BankAccount();

		
		return null;
//		List<Transaction> list = new ArrayList<Transaction>();
//		Account acc = new Account(1,"hi",12.12,"hello",time,type,user,list);
//		
//		System.out.println(acc);
		
//		return acc;
>>>>>>> 447db8d969d73cf54e8cd9fb11e88f3022835b86
	}

}
