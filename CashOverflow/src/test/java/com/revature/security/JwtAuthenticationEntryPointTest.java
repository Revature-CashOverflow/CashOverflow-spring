package com.revature.security;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.AuthenticationException;

@SpringBootTest
class JwtAuthenticationEntryPointTest {

	@Mock
	HttpServletRequest req;
	
	@Mock
	HttpServletResponse resp;
	
	@Mock
	AuthenticationException authException;
	
	@Mock
	JwtAuthenticationEntryPoint ep;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testCommence() throws IOException, ServletException {
		ep.commence(req, resp, authException);
		verify(resp, times(1)).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}

}
