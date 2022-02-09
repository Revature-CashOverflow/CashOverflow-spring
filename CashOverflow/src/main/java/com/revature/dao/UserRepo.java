package com.revature.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.User;

/**
 * 
 * @author rasco
 *
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	/***
	 * Method to login to the application
	 * @param username - username enter to log in
	 * @param password - password enter to log in
	 * @return - User object
	 */
	public User findByUsernameAndPassword(String username, String password);
}
