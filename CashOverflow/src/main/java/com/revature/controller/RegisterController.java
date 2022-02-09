package com.revature.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.revature.model.User;
import com.revature.service.UserService;

@Controller
public class RegisterController {
	
	private UserService userServ;
	
//	@Autowired
//	public RegisterController(RegisterService regServ) {
//		this.regServ = regServ;
//	}
	
	/**
	 * 
	 * @authors Cameron, Amir, Chandra
	 */
	@PostMapping("/newUser")
	@ResponseStatus(HttpStatus.CREATED)
	public User newUser(@RequestBody User user) {
		
		return userServ.insertUser(user); //expecting the service method to return null if their is duplicate data
		
	}
	
	
	

}
