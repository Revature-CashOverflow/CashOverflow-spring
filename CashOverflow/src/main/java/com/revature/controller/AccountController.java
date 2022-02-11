package com.revature.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.revature.dto.BankAccountDto;
import com.revature.model.BankAccount;
import com.revature.service.BankAccountService;

@CrossOrigin(origins = "*")
@Controller
public class AccountController {

	private BankAccountService bankAccServ;

	@Autowired
	public AccountController(BankAccountService bankAccServ) {
		this.bankAccServ = bankAccServ;
	}

	/**
	 * @param newAccount
	 * @apiNote json params: name, description, accountTypeId, "user": jwt
	 *
	 * @return BankAccount
	 * 
	 * @author Parker Mace, Henry Harvil, Andre Long
	 */
	@PostMapping("/api/account/createBankAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody BankAccountDto createBankAccount(@RequestBody BankAccount newAccount) {
		/*
		 * TODO: we will need to generate a user object using a jwt instead of passing
		 * in the object as a part of the json. this means we will have to set `"user":
		 * UserAccount` in the request body within this method
		 */
		
//		// here we will be using a jwt to assign Account.user to a com.revature.model.User object
//		BankAccount aux = bankAccServ.createAccount(newAccount);
//		BankAccount responseBankAccount = new BankAccount();
//		responseBankAccount.setName(aux.getName());
//		return responseBankAccount;

		// here we will be using a jwt to assign Account.user to a
		// com.revature.model.User object

		return new BankAccountDto(bankAccServ.createAccount(newAccount));
	}

	/**
	 * 
	 * @return List<BankAccountDto>
	 * 
	 * @author Parker Mace
	 */
	@GetMapping("/api/account/getBankAccounts")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<BankAccountDto> getBankAccounts(HttpServletRequest req) {
		/*
		 * TODO: we will generate a user object from their jwt and check to ensure
		 * req.getParameter("id") == UserAccount.getId(); If it does not, tell them to
		 * go away
		 */

		/*
		 * This strips sensitive info out of the List<BankAccount> return in order to
		 * return List<BankAccountDto>
		 */
//		List<BankAccount> accounts = new ArrayList<BankAccount>();
//		
//		accounts = bankAccServ.getBankAccounts(req);
//		
////		return acc;
//		return accounts;
		return bankAccServ.getBankAccounts(req).stream().map(BankAccount -> new BankAccountDto(BankAccount))
				.collect(Collectors.toList());
	}
}
