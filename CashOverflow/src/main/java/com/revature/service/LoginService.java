package com.revature.service;

import com.revature.model.UserAccount;
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
	 * @return - UserAccount object
	 */
	public UserAccount login(String username, String password);
}
