package com.revature.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.revature.controller.LoginController;
import com.revature.dto.LoginRequestDto;
import com.revature.dto.LoginUserAccountDto;
import com.revature.model.UserAccount;
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
	ModelMapper mapper;
	
	@BeforeEach
	void setup() {
		loginController = new LoginController(serv,mapper);
	}
	
	@Test
	void loginFailTest() {
		LoginRequestDto req = new LoginRequestDto(null,null);
		ResponseStatusException e = assertThrows(ResponseStatusException.class,()->{
			loginController.login(req);
		});
		
		String expectedReason = "missing Credential";
		HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
		Integer expectedCode = 400;
		
		assertEquals(expectedReason,e.getReason());
		assertEquals(expectedStatus,e.getStatus());
		assertEquals(expectedCode,e.getRawStatusCode());
	}
	
	@Test
	void loginSuccessTest() {
		LoginRequestDto req = new LoginRequestDto("dummy","password");
		UserAccount fake = new UserAccount();
		fake.setUsername("dummy");
		fake.setPassword("password");
		LoginUserAccountDto initial = new LoginUserAccountDto();
		initial.setUsername("dummy");
		LoginUserAccountDto expected = new LoginUserAccountDto();
		expected.setUsername("dummy");
		
		when(serv.login("dummy","password")).thenReturn(fake);
		when(mapper.map(fake, LoginUserAccountDto.class)).thenReturn(initial);
		
		LoginUserAccountDto actual = loginController.login(req);
		
		assertEquals(actual,expected);
		
		
	}
	
	
	
}
