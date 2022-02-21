package com.revature.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.BankAccountDto;
import com.revature.model.BankAccount;
import com.revature.model.FundTransfer;
import com.revature.model.UserAccount;
import com.revature.service.BankAccountService;
import com.revature.service.UserAccountService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://dostz94b44kp0.cloudfront.net" })
public class AccountController {

	private BankAccountService bankAccServ;
	private UserAccountService userAccServ;
	private ModelMapper mapper;

	@Autowired
	public AccountController(BankAccountService bankAccServ, ModelMapper mapper, UserAccountService userAccServ) {
		this.bankAccServ = bankAccServ;
		this.mapper = mapper;
		this.userAccServ = userAccServ;
	}

	/**
	 * @param newAccount
	 * @apiNote json params: name, description, accountTypeId
	 *
	 * @return BankAccountDto
	 * 
	 * @author Parker Mace, Henry Harvil, Andre Long
	 */
	@PostMapping("/api/account/createBankAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccountDto createBankAccount(Authentication auth, @RequestBody BankAccountDto dtoAccount) {
		System.out.println(dtoAccount);
		BankAccount account = convertToEntity(dtoAccount);

		account.setUser(userAccServ.getUserFromUsername(auth.getName()));

		return convertToDto(bankAccServ.createAccount(account));
	}

	/**
	 * @return List<BankAccountDto>
	 * 
	 * @author Parker Mace
	 */
	@GetMapping("/api/account/getBankAccounts")
	@ResponseStatus(HttpStatus.OK)
	public List<BankAccountDto> getBankAccounts(Authentication auth) {

		return bankAccServ.getBankAccounts(userAccServ.getUserFromUsername(auth.getName()).getId()).stream()
				.map(this::convertToDto).collect(Collectors.toList());
	}

	/**
	 * @param FundTransfer { 
	 * 				transferFromAccount: String account,
	 * 				transferToAccount: String account,
	 * 				transferAmount: Double amount 
	 * 			}
	 * 
	 * @return List<BankAccountDto>
	 * 
	 *         This transaction will fail and do nothing if the user cannot afford
	 *         the specified tx, or if an invalid number is given
	 * 
	 * @author Parker Mace
	 */
	@PostMapping("/api/account/transferFunds")
	@ResponseStatus(HttpStatus.OK)
	public List<BankAccountDto> transferFunds(Authentication auth, @RequestBody FundTransfer fundTransfer) {
		UserAccount user = userAccServ.getUserFromUsername(auth.getName());
		return bankAccServ.transferFunds(user, fundTransfer).stream().map(this::convertToDto)
				.collect(Collectors.toList());

	}

	protected BankAccount convertToEntity(BankAccountDto dtoAccount) {
		return mapper.map(dtoAccount, BankAccount.class);
	}

	protected BankAccountDto convertToDto(BankAccount account) {
		return mapper.map(account, BankAccountDto.class);
	}

}
