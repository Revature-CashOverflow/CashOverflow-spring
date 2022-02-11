package com.revature.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.revature.model.JwtRequest;
import com.revature.service.JwtAuthenticationService;

@SpringBootTest 
class SessionManagementTests {

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
			assertEquals(HttpStatus.OK, serv.createAuthenticationToken(req).getStatusCode());
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	void createAuthenicationTokenBadPassword() {
		try {
			assertThrows(Exception.class, ()->{serv.createAuthenticationToken(badPasswordReq);});
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	void createAuthenicationTokenBadUsername() {
		try {
			assertThrows(Exception.class, ()->{serv.createAuthenticationToken(badUsernameReq);});
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	void badRequestEmpty() {
		try {
			assertThrows(Exception.class, ()->{serv.createAuthenticationToken(emptyReq);});
		}catch(Exception e) {
			fail();
		}
	}
}
