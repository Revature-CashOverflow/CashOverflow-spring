package com.revature.service;

import com.revature.dao.UserAccountRepo;
import com.revature.model.UserAccount;

public class UserAccountServiceImpl implements UserAccountService {
	
	UserAccountRepo userAccountRepo;
	
	
	/**
	 * 
	 * @authors Cameron, Amir, Chandra
	 */
	@Override
	public UserAccount insertUserAccount(UserAccount user) {
		return userAccountRepo.save(user);
	}
	
	

}
