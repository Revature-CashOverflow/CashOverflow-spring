package com.revature.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.revature.model.UserAccount;

public class UserAccountDtoTest {
	
	@Mock
	UserAccount uAcc = new UserAccount();
	
	@Mock
	UserAccountDto dto = new UserAccountDto(uAcc);
	
	@Test
	public void testId() {
		int id = 12;
		dto.setId(13);
		assertEquals(dto.getId(), 13);
	}
	
	@Test
	public void testUsername() {
		String username = "hudsonalec";
		dto.setUsername("ahhudson");
		assertEquals(dto.getUsername(), "ahhudson");
	}
	
	@Test
	public void testFirstName() {
		String firstName = "Alec";
		dto.setFirstName("joe");
		assertEquals(dto.getFirstName(), "joe");
	}
	
	@Test
	public void testLastName() {
		String lastName = "flo";
		dto.setLastName("rida");
		assertEquals(dto.getLastName(), "rida");
	}
	
	@Test 
	public void testPassword() {
		String password = "pass";
		dto.setPassword("word");
		assertEquals(dto.getPassword(), "word");
	}
	
//	@Test
//	public void testCreationDate() {
//		
//	}

}
