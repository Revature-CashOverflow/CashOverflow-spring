package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.JwtRequest;
import com.revature.model.JwtResponse;
import com.revature.service.JwtUserDetailsService;
import com.revature.util.JwtUtil;

/**
 * Class to handle JWT Authentication
 * 
 * @author Tyler Rondeau, Luis Estevez, Luis Rivera
 *
 */
@RestController
@CrossOrigin
public class JwtAuthenticationController {

	
	private JwtUtil util;
	private JwtUserDetailsService serv;
	private AuthenticationManager manager;

	@Autowired
	public JwtAuthenticationController(JwtUtil util, JwtUserDetailsService serv, AuthenticationManager manager) {
		this.util = util;
		this.serv = serv;
		this.manager = manager;
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = serv.loadUserByUsername(authenticationRequest.getUsername());

		final String token = util.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
