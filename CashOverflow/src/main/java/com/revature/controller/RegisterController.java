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

import lombok.AllArgsConstructor;

@CrossOrigin(value = "http://localhost:4200")
@Controller
public class RegisterController {
	
	/**
	 * 
	 * @author cam77 + scrummybois
	 *
	 */
	@AllArgsConstructor
	private class UserDto {
		String email;
		String username;
		String firstName;
		String lastName;
		String password;
		
	}
	
	
	
	private RegisterService regServ;
	
	@Autowired
	public RegisterController(RegisterService regServ) {
		this.regServ = regServ;
	}
	
	
	/**
	 * TODO: explain dto thing, password mismatch, error handling, + controller testing
	 * 
	 * @authors Cameron, Amir, Chandra
	 */
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public void newUser(@RequestBody UserDto userDto) {
		UserAccount user = new UserAccount();
		user.setEmail(userDto.email);
		user.setUsername(userDto.username);
		user.setFirstName(userDto.firstName);
		user.setLastName(userDto.lastName);
		user.setPassword(userDto.password);
		regServ.insertUserAccount(user);
		
	}
	
	
	

}
