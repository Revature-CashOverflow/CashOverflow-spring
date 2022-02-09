package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.UserAccount;
import com.revature.service.LoginService;

@RestController
public class LoginController {
	@Autowired
	LoginService serv;
	
	@GetMapping("/login")
	public @ResponseBody UserAccount login(@RequestParam ("loginUsername") String username, @RequestParam("loginPassword") String password) {
		UserAccount output = serv.login(username, password);
		System.out.println(output);
		
		return output;
	}
}
