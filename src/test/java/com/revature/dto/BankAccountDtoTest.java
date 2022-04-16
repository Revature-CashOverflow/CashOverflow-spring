package com.revature.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.yaml.snakeyaml.tokens.DocumentEndToken;

public class BankAccountDtoTest {
	

	@Mock BankAccountDto dto = new BankAccountDto();
	
	@Test
	public void testId() {
		int id = 2;
		dto.setId(1);
		assertEquals(dto.getId(), 1);		
	}
	
	@Test
	public void testName() {
		String name = "Chuck Norris";
		dto.setName("Worst nightmare");
		assertEquals(dto.getName(), "Worst nightmare");			
	}
	
	@Test
	public void testBalance() {
		Double balance = 68.99;
		dto.setBalance(21.00);
		assertEquals(dto.getBalance(), 21.00);
	}
	
	@Test
	public void testDescription() {
		String description = "Money to ball";
		dto.setDescription("Rent");
		assertEquals(dto.getDescription(), "Rent");
	}
	
	@Test
	public void testSetterCreationDate() {
		/**
		 * TODO: Test Instant type?
		 */
	}
	
	@Test
	public void testAccountTypeId() {
		int accountTypeId = 1;
		dto.setAccountTypeId(2);
		assertEquals(dto.getAccountTypeId(), 2);
	}

}
