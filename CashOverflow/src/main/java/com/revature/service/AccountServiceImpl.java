package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.AccountRepo;

@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountRepo accountRepo;

	@Autowired
	public void setUserRepo(AccountRepo accountRepo) {
		this.accountRepo = accountRepo;
	}
}
