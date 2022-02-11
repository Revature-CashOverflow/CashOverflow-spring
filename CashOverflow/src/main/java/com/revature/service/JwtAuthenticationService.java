package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.model.JwtRequest;
import com.revature.model.JwtResponse;
import com.revature.util.JwtUtil;

/**
 * Class to handle JWT Authentication
 * 
 * @author Tyler Rondeau, Luis Estevez, Luis Rivera
 *
 */
@Service
public class JwtAuthenticationService {

	private JwtUtil util;
	private JwtUserDetailsService serv;
	private AuthenticationManager manager;

	@Autowired
	public JwtAuthenticationService(JwtUtil util, JwtUserDetailsService serv, AuthenticationManager manager) {
		this.util = util;
		this.serv = serv;
		this.manager = manager;
	}

	/**
	 * Method to create an AutheticationToken (to be called when the user logs in)
	 * 
	 * @param authenticationRequest - Incoming request
	 * @return - Response to front-end
	 * @throws Exception
	 */
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = serv.loadUserByUsername(authenticationRequest.getUsername());

		final String token = util.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	/**
	 * Create the authentication itself
	 * 
	 * @param username - Username of user to authenticate
	 * @param password - password of user to authenticate
	 * @throws Exception
	 */
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