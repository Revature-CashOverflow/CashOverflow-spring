package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.UserAccountDto;
import com.revature.model.UserAccount;
import com.revature.service.LoginService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://3.92.176.100"})
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
	 * 
	 * @author Emmanuel Sosa
	 */
	@PostMapping(value = "/login")
	public UserAccountDto login(@RequestBody UserAccount user ) {
		System.out.println(user);
		
		UserAccountDto userDto = new UserAccountDto( user);

		//		System.out.println("loginUser.getUsername()===>" + userAccount.getUsername());
//		UserAccount user = serv.login(userAccount.getUsername(), userAccount.getPassword());
//		if (user == null) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Credentials");
//		}
		return userDto;

	}

}
