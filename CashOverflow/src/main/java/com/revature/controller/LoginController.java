package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
=======
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
>>>>>>> 032743252f98acbd948afe1212e867ca9b0b1055
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dto.UserAccountDto;
import com.revature.model.UserAccount;
import com.revature.service.LoginService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://3.92.176.100"})
public class LoginController {

<<<<<<< HEAD
=======
	LoginService serv;

>>>>>>> 032743252f98acbd948afe1212e867ca9b0b1055
	@Autowired
	LoginService serv;

	public LoginController(LoginService serv) {
		this.serv = serv;
	}

<<<<<<< HEAD
	@CrossOrigin(origins="http://localhost:4200")
=======
>>>>>>> 032743252f98acbd948afe1212e867ca9b0b1055
	/**
	 * Checks if the UserAccount name & password matches credential in the database
	 * 
	 * @param username
	 * @param password
	 * @return login User
	 * 
	 * @author Emmanuel Sosa
	 */
<<<<<<< HEAD
	@GetMapping(value = "/login")
	public UserAccount login(@RequestParam("loginUsername") String username,
			@RequestParam("loginPassword") String password) {
		System.out.println("in back end");
		UserAccount user = serv.login(username, password);

		return user;
=======
	@PostMapping(value = "/login")
	public UserAccountDto login(UserAccount loginUser) {
		UserAccount user = serv.login(username, password);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Credentials");
		}
		return new UserAccountDto(user);
>>>>>>> 032743252f98acbd948afe1212e867ca9b0b1055

	}

}
