package com.revature.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.web.server.ResponseStatusException;

import com.revature.controller.LoginController;
import com.revature.dto.LoginRequestDto;
import com.revature.model.JwtResponse;
import com.revature.model.UserAccount;
import com.revature.service.JwtAuthenticationService;
import com.revature.service.LoginService;

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
	LoginService serv;

	@Mock
	JwtAuthenticationService jwtServ;
	

	@BeforeEach
	void setup() {
		loginController = new LoginController(serv, jwtServ);
	}

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

	@Test
	void loginSuccessTest() {
		LoginRequestDto req = new LoginRequestDto("dummy", "password");
		UserAccount initial = new UserAccount("dummy", "password");

		ResponseEntity<JwtResponse> resp = new ResponseEntity<JwtResponse>(new JwtResponse("Words"), HttpStatus.OK);
		when(serv.login("dummy", "password")).thenReturn(initial);
		when(jwtServ.createAuthenticationToken(initial.getUsername(), initial.getPassword())).thenReturn(resp);
		ResponseEntity<JwtResponse> result = loginController.login(req, null);

		assertEquals(result, resp);

	}

}
