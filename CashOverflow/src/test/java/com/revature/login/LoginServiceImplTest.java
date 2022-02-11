package com.revature.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.dao.UserAccountRepo;
import com.revature.model.UserAccount;
import com.revature.service.LoginService;
import com.revature.service.LoginServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LoginServiceImplTest {

	@Mock
	private UserAccountRepo repo;
	@Mock
	private LoginService serv;
	@Mock
	private UserAccount initial, expected;

	@BeforeEach
	void setUp() throws Exception {
		serv = new LoginServiceImpl(repo);
		initial = new UserAccount("dummy", "password");
		expected = new UserAccount("dummy", "password");
	}

	@Test
	void login2() {
		when(repo.findByUsernameAndPassword("dummy", "password")).thenReturn(initial);

		UserAccount works = serv.login("dummy", "password");
		System.out.println(works);
		assertEquals(works, expected);

	};
}
