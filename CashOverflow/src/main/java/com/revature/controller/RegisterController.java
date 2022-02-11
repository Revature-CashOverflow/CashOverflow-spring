package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.revature.model.UserAccount;
import com.revature.service.RegisterService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@CrossOrigin(value = "http://localhost:4200")
@Controller
public class RegisterController {
	
	/**
	 * 
	 * @author cam77 + scrummybois
	 *
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class UserDto {
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
	 * TODO: password mismatch, error handling, + controller testing
	 * In order to guard against SQL injection we have created a data transfer object to act as a buffer 
	 * @authors Cameron, Amir, Chandra
	 */
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public void newUser(@RequestBody UserDto userDto) {
		if (userDto.email == null || userDto.username == null || userDto.firstName == null || userDto.lastName == null || userDto.password == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing registration info");
		}
		UserAccount user = new UserAccount();
		user.setEmail(userDto.email);
		user.setUsername(userDto.username);
		user.setFirstName(userDto.firstName);
		user.setLastName(userDto.lastName);
		user.setPassword(userDto.password);
		regServ.insertUserAccount(user);
		
	}
	
	
	
	
	

}
