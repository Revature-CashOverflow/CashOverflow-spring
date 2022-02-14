package com.revature.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dto.LoginRequestDto;
import com.revature.dto.LoginUserAccountDto;
import com.revature.model.UserAccount;
import com.revature.service.LoginService;

/**
 * This Class is use to handle login functionality
 * 
 * @author Emmanuel Sosa, Liliya Sherstobitova, Delane Chen
 *
 */
@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://d3nlmo2v0fs5mq.cloudfront.net"})
public class LoginController {
	
	private ModelMapper mapper;	
	
	private LoginService serv;

	@Autowired
	public LoginController(LoginService serv, ModelMapper mapper) {
		this.serv = serv;
		this.mapper = mapper;
	}
	

	/**
	 * Checks if the User name & password matches credential in the database
	 * 
	 * @param username
	 * @param password
	 * @return login User
	 * 
	 * @author Emmanuel Sosa, Liliya Sherstobitova, Delane Chen
	 */
	@PostMapping(value = "/login")
	public LoginUserAccountDto login(@RequestBody LoginRequestDto req) {
		if(req.getUsername() == null || req.getPassword() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"missing Credential");
		}
		UserAccount user = serv.login(req.getUsername(), req.getPassword());
		
		
		return convertToDto(user);

	}
	
	private LoginUserAccountDto convertToDto(UserAccount userAccount) {
		return mapper.map(userAccount, LoginUserAccountDto.class);
	}
}