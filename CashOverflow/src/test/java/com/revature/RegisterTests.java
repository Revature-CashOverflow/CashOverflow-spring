package com.revature;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.dao.UserAccountRepo;
import com.revature.model.UserAccount;
import com.revature.service.RegisterServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RegisterTests {

	private RegisterServiceImpl regServ;

	@Mock
	private UserAccountRepo mockRepo;

	@BeforeEach
	public void setUp() {
		regServ = new RegisterServiceImpl(mockRepo);
	}

	@Test
	void serviceTest() {
		UserAccount test = new UserAccount(3, "email@gmail.com", "username", "firstname", "lastname", "password", null);
		UserAccount expected = new UserAccount(3, "email@gmail.com", "username", "firstname", "lastname", "password",
				null);
		
		when(mockRepo.save(test)).thenReturn(test);
		UserAccount result = regServ.insertUserAccount(test);

		assertNotNull(result.getCreationDate());

	}

}
