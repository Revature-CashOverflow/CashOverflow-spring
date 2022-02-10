package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.UserAccount;
import com.revature.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	LoginService serv;

	public LoginController(LoginService serv) {
		this.serv = serv;
	}

	@CrossOrigin(origins="http://localhost:4200")
	/**
	 * Checks if the UserAccount name & password matches credential in the database
	 * 
	 * @param username
	 * @param password
	 * @return login User
	 */
	@GetMapping(value = "/login")
	public UserAccount login(@RequestParam("loginUsername") String username,
			@RequestParam("loginPassword") String password) {
		System.out.println("in back end");
		UserAccount user = serv.login(username, password);

		return user;

	}

}
