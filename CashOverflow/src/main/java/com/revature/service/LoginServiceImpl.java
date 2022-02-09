package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.UserAccountRepo;
import com.revature.model.UserAccount;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserAccountRepo repo;

	
	@Autowired
	public LoginServiceImpl(UserAccountRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public UserAccount login(String username, String password) {
		return repo.findByUsernameAndPassword(username, password);
	}
}
