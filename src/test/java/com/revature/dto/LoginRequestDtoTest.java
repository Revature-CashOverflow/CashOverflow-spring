package com.revature.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class LoginRequestDtoTest {
	
	@Mock
	LoginRequestDto dto = new LoginRequestDto(null, null);
	
	@Test
	public void testUsername() {
		String username = "Alec";
		dto.setUsername("Alex");
		assertEquals(dto.getUsername(), "Alex");
	}
	
	@Test
	public void testPassword() {
		String password = "password";
		dto.setPassword("not password");
		assertEquals(dto.getPassword(), "not password");
	}

}
