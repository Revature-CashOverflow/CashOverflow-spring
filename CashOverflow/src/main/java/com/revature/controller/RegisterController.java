package com.revature.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dto.RegUserAccountDto;
import com.revature.model.UserAccount;
import com.revature.service.RegisterService;

@CrossOrigin(value = {"http://localhost:4200", "http://d3nlmo2v0fs5mq.cloudfront.net"})
@Controller
public class RegisterController {
	
	private RegisterService regServ;
	private ModelMapper mapper;
	
	
	@Autowired
	public RegisterController(RegisterService regServ, ModelMapper mapper) {
		this.regServ = regServ;
		this.mapper = mapper;
	}
	
	private UserAccount convertToEntity(RegUserAccountDto dto) {
		return mapper.map(dto, UserAccount.class);
	}
	
	/**
	 * TODO: password mismatch, error handling, + controller testing
	 * In order to guard against SQL injection we have created a data transfer object to act as a buffer 
	 * @authors Cameron, Amir, Chandra
	 */
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public void newUser(@RequestBody RegUserAccountDto dto) {
		if (dto.getEmail() == null || dto.getUsername() == null || dto.getFirstName() == null || dto.getLastName() == null || dto.getPassword() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing registration info");
		}
		UserAccount user = convertToEntity(dto);
		regServ.insertUserAccount(user);
		
	}
	
	
	
	
	

}
