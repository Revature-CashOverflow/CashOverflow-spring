package com.revature.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.service.LoginService;

@RestController
public class LoginController {

	LoginService serv;
	
	public User login(@RequestBody Map<String, Object> json) {
		
	}

}
