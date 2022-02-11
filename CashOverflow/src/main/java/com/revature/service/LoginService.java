package com.revature.service;

import com.revature.dto.UserAccountDto;
/**
 * 
 * @author emmanuel
 *
 */
public interface LoginService {

	/**
	 * Method to login to the application
	 * @param username - username enter to log in
	 * @param password - password enter to log in
	 * @return - User object
	 */
	public UserAccountDto login(String username, String password);
}
