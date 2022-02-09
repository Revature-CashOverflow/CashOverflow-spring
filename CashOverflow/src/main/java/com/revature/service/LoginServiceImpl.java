package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.UserRepo;
import com.revature.model.User;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserRepo repo;
	
	@Override
	public User login(String username, String password) {
		return repo.findByUsernameAndPassword(username, password);
	}
}
