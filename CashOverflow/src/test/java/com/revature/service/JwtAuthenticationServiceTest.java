package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;

import com.revature.model.JwtRequest;


@SpringBootTest
class JwtAuthenticationServiceTest {

	@Autowired
	JwtAuthenticationService serv;
	static JwtRequest req;
	static JwtRequest badPasswordReq;
	static JwtRequest badUsernameReq;
	static JwtRequest emptyReq;

	@BeforeAll
	static void setUp() {
		req = new JwtRequest("user", "password");
		badPasswordReq = new JwtRequest("user", "pasword");
		badUsernameReq = new JwtRequest("li", "password");
		emptyReq = new JwtRequest();
	}
	
	@Test
	void createAuthenticationToken() {
		try {
			assertEquals(HttpStatus.OK, serv.createAuthenticationToken("user", "password").getStatusCode());
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	void createAuthenicationTokenBadPassword() {
		try {
			assertThrows(BadCredentialsException.class, ()->{serv.createAuthenticationToken("user", "pasword");});
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	void createAuthenicationTokenBadUsername() {
		try {
			assertThrows(BadCredentialsException.class, ()->{serv.createAuthenticationToken("li", "password");});
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	void badRequestEmpty() {
		try {
			assertThrows(BadCredentialsException.class, ()->{serv.createAuthenticationToken(null, null);});
		}catch(Exception e) {
			fail();
		}
	}
	
	
}
