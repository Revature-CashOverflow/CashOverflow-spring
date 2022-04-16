package com.revature.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class RegUserAccountDtoTest {
	
	@Mock
	RegUserAccountDto dto = new RegUserAccountDto(null, null, null, null, null);
	
	@Test
	public void testEmail() {
		String email = "dog@gmail.com";
		dto.setEmail("cat@gmail.com");
		assertEquals(dto.getEmail(), "cat@gmail.com");
	}
	
	@Test
	public void testUsername() {
		String username = "bigBenz";
		dto.setUsername("biggerBenz");
		assertEquals(dto.getUsername(), "biggerBenz");
	}
	
	@Test
	public void testFirstName() {
		String firstName = "Sarah";
		dto.setFirstName("Abby");
		assertEquals(dto.getFirstName(), "Abby");
	}
	
	@Test
	public void testLastName() {
		String lastName = "Johnson";
		dto.setLastName("Jefferson");
		assertEquals(dto.getLastName(), "Jefferson");
	}
	
	@Test
	public void testPassword() {
		String password = "letsgo";
		dto.setPassword("NoGo");
		assertEquals(dto.getPassword(), "NoGo");
	}



}
