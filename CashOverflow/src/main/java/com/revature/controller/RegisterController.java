package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.revature.model.UserAccount;
import com.revature.service.RegisterService;

@CrossOrigin(value = "http://localhost:4200")
@Controller
public class RegisterController {
	
	
	private RegisterService regServ;
	
	@Autowired
	public RegisterController(RegisterService regServ) {
		this.regServ = regServ;
	}
	
	
	/**
	 * 
	 * @authors Cameron, Amir, Chandra
	 */
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public void newUser(@RequestBody UserAccount user) {
		
		regServ.insertUserAccount(user);
		
	}
	
	
	

}
