package com.revature.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	LoginService serv;

	 @GetMapping("/login")
	    public User login(@RequestParam ("loginUsername") String username, @RequestParam ("loginPassword") String password) {
	        System.out.println("username===>" + username);
	        System.out.println("password===>" + password);

	        User user = serv.login(username, password);
	        return user;
	    }

	}

