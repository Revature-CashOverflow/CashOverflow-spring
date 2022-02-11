package com.revature.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.revature.dto.BankAccountDto;
import com.revature.model.BankAccount;
import com.revature.service.BankAccountService;
import com.revature.util.JwtAccessUtil;

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
	 * @return BankAccountDto
	 * 
	 * @author Parker Mace, Henry Harvil, Andre Long
	 */
	@PostMapping("/api/account/createBankAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody BankAccountDto createBankAccount(@RequestBody BankAccountDto dtoAccount,
			@RequestHeader("Authorization") String token) {

		BankAccount account = new BankAccount();
		account.setName(dtoAccount.getName());
		account.setDescription(dtoAccount.getDescription());
		account.setAccountTypeId(dtoAccount.getAccountTypeId());
		account.setUser(JwtAccessUtil.getUserFromToken(token));

		return new BankAccountDto(bankAccServ.createAccount(account));
	}

	/**
	 * 
	 * @return List<BankAccountDto>
	 * 
	 * @author Parker Mace
	 */
	@GetMapping("/api/account/getBankAccounts")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<BankAccountDto> getBankAccounts(@RequestHeader("Authorization") String token) {

		// This strips sensitive info out of the List<BankAccount> return in order to
		// return List<BankAccountDto>
		return bankAccServ.getBankAccounts(JwtAccessUtil.getUserFromToken(token).getId()).stream()
				.map(BankAccountDto::new).collect(Collectors.toList());
	}
}
