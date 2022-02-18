package com.revature.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import com.revature.controller.LoginController;
import com.revature.dto.LoginRequestDto;
import com.revature.model.JwtResponse;
import com.revature.model.UserAccount;
import com.revature.service.JwtAuthenticationService;
import com.revature.service.UserAccountService;

/**
 * 
 * Tests login controller functionality.
 * 
 * @author Delane Chen, Liliya Sherstobitova, Emmanuel Sosa
 *
 */

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

	LoginController loginController;

	@Mock
	UserAccountService serv;

	@Mock
	JwtAuthenticationService jwtServ;

	@Mock
	PasswordEncoder enc;

	@BeforeEach
	void setup() {
		loginController = new LoginController(serv, jwtServ, enc);
	}

	// Null credential failure
	@Test
	void loginFailTest() {
		LoginRequestDto req = new LoginRequestDto(null, null);
		ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
			loginController.login(req, null);
		});

		String expectedReason = "missing Credential";
		HttpStatus expectedStatus = HttpStatus.NOT_ACCEPTABLE;
		Integer expectedCode = 406;

		assertEquals(expectedReason, e.getReason());
		assertEquals(expectedStatus, e.getStatus());
		assertEquals(expectedCode, e.getRawStatusCode());
	}

	// Bad credential failure
	@Test
	void loginFailureTest() {
		LoginRequestDto req = new LoginRequestDto("dummy", "padsojgfhldsoajord");
		UserAccount initial = new UserAccount("dummy", enc.encode("password"));

		when(serv.getUserFromUsername("dummy")).thenReturn(initial);
		ResponseEntity<JwtResponse> result = loginController.login(req, new MockHttpServletResponse());
		System.out.println(result);
		assertNull(result);
	}

	// Valid credentials login
	@Test
	void loginSuccessTest() {
		LoginRequestDto req = new LoginRequestDto("dummy", "password");
		UserAccount initial = new UserAccount("dummy", enc.encode("password"));
		ResponseEntity<JwtResponse> expected = jwtServ.createAuthenticationToken(initial.getUsername(),
				initial.getPassword());

		when(serv.getUserFromUsername("dummy")).thenReturn(initial);

		ResponseEntity<JwtResponse> actual = loginController.login(req, new MockHttpServletResponse());

		System.out.println(actual);
		assertEquals(expected, actual);
	}
}
