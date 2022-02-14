package com.revature.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.model.UserAccount;
import com.revature.service.UserAccountService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class JwtAccessUtilTest {

	@InjectMocks
	static JwtAccessUtil aUtil;
	
	@Mock
	static JwtUtil util;
	
	@Mock
	static UserAccountService serv;
	
	@SuppressWarnings("static-access")
	@BeforeEach
	void setUp() {
		aUtil.util = util;
		aUtil.serv = serv;
	}
	
	@SuppressWarnings("static-access")
	@Test
	void testGetUsernameFromToken() {
		String token = "false token";
		when(util.getUsernameFromToken(token)).thenReturn("false username");
		String expected = "false username";
		String actual = aUtil.getUsernameFromToken(token);
		assertEquals(expected, actual);
	}
	
	@SuppressWarnings("static-access")
	@Test
	void testGetUserFromToken() {
		String token = "false token";
		UserAccount initial = new UserAccount();
		initial.setUsername("false username");
		when(aUtil.getUsernameFromToken(token)).thenReturn("false username");
		when(serv.getUserFromUsername("false username")).thenReturn(initial);
		UserAccount expected = new UserAccount();
		expected.setUsername("false username");
		UserAccount actual = aUtil.getUserFromToken(token);
		verify(serv, times(1)).getUserFromUsername(initial.getUsername());
		assertEquals(expected, actual);
	}

}
