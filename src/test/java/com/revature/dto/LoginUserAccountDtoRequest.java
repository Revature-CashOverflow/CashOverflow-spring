package com.revature.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class LoginUserAccountDtoRequest {
	
	@Mock
	LoginUserAccountDto dto = new LoginUserAccountDto();
	
	@Test
	void testId() {
		int id = 2;
		dto.setId(3);
		assertEquals(dto.getId(), 3);
	}
	
	@Test
	void testUsername() {
		String username = "hudson_alec";
		dto.setUsername("ahhudson");
		assertEquals(dto.getUsername(), "ahhudson");
	}
	
	@Test 
	void testFirstName() {
		String firstName = "Alec";
		dto.setFirstName("Collin");
		assertEquals(dto.getFirstName(), "Collin");
	}
	
	@Test
	void testLastName() {
		String lastName = "Hudson";
		dto.setLastName("Jackson");
		assertEquals(dto.getLastName(), "Jackson");
	}
	
	@Test
	void testCreationDate() {
		/**
		 * TODO: test Instant type?
		 */
	}

}
