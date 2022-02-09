package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.UserAccount;
import com.revature.service.LoginService;

@RestController
public class LoginController {

	
	LoginService serv;
	
	@Autowired
	public LoginController(LoginService serv) {
		this.serv = serv;
	}

	
	/**
	 * Checks if the User name & password matches credential in the database
	 * 
	 * @param username
	 * @param password
	 * @return login User
	 */
	@GetMapping(value = "/login")
	public UserAccount login(@RequestParam("loginUsername") String username,@RequestParam("loginPassword") String password) {
		UserAccount user = serv.login(username,password);
		
			return user;
			
	}

	}

