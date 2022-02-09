package com.revature.service;

import com.revature.dao.UserRepo;
import com.revature.model.User;

public class UserServiceImpl implements UserService {
	
	UserRepo userRepo;
	
	
	/**
	 * 
	 * @authors Cameron, Amir, Chandra
	 */
	@Override
	public User insertUser(User user) {
		return userRepo.save(user);
	}
	
	

}
