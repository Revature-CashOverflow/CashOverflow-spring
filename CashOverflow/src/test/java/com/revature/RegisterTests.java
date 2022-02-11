package com.revature;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.controller.RegisterController;
import com.revature.dao.UserAccountRepo;
import com.revature.model.UserAccount;
import com.revature.service.RegisterService;
import com.revature.service.RegisterServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RegisterTests {

	private RegisterServiceImpl regServ;
	private RegisterController regCont;

	@Mock
	private UserAccountRepo mockRepo;
	
	@Mock
	private RegisterService mockServ;
	
	
	@BeforeEach
	public void setUp() {
		regServ = new RegisterServiceImpl(mockRepo);
		regCont = new RegisterController(mockServ);
		
		
	}

	@Test
	void serviceTest() {
		UserAccount test = new UserAccount(3, "email@gmail.com", "username", "firstname", "lastname", "password", null);
		UserAccount expected = new UserAccount(3, "email@gmail.com", "username", "firstname", "lastname", "password",
				null);
		
		when(mockRepo.save(test)).thenReturn(test);
		UserAccount result = regServ.insertUserAccount(test);
		verify(mockRepo, times(1)).save(test);
		assertNotNull(result.getCreationDate());

	}
	
	@Test
	void controllerTest() {
		RegisterController.UserDto test = new RegisterController.UserDto("email@gmail.com", "username", "firstname", "lastname", "password");
		
		UserAccount user = new UserAccount();
		user.setEmail(test.getEmail());
		user.setUsername(test.getUsername());
		user.setFirstName(test.getFirstName());
		user.setLastName(test.getLastName());
		user.setPassword(test.getPassword());
		
		verify(regServ, times(1)).insertUserAccount(user);
		
	}
	
	

}
