package com.revature.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.service.LoginService;

@RestController
public class LoginController {

	
	LoginService serv;

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
	public User login(@RequestParam("loginUsername") String username,@RequestParam("loginPassword") String password) {
		User user = serv.login(username,password);
		
			return user;
			
	}

	}

