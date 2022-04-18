package com.revature.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class TransferBetweenDtoTest {
	
	@Mock
	TransferBetweenDto dto = new TransferBetweenDto();
	
	@Test
	void testFromAccount() {
		int fromAccount = 1;
		dto.setFromAccount(2);
		assertEquals(dto.getFromAccount(), 2);
	}
	
	@Test
	void testToAccount() {
		int toAccount = 2;
		dto.setToAccount(3);
		assertEquals(dto.getToAccount(), 3);
	}
	
	@Test
	void testAmount() {
		double amount = 10.00;
		dto.setAmount(20.00);
		assertEquals(dto.getAmount(), 20.00);
	}

}
