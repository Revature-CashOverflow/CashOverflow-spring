package com.revature.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class TransactionDtoTest {
	
	@Mock
	TransactionDto dto = new TransactionDto(0, 0, null, null, null);
	
	@Test
	void testAccountId() {
		int accountId = 1;
		dto.setAccountId(2);
		assertEquals(dto.getAccountId(), 2);
	}
	
	@Test
	void testTxTypeId() {
		int txTypeId = 1;
		dto.setTxTypeId(2);
		assertEquals(dto.getTxTypeId(), 2);	
	}
	
	@Test
	void testAmount() {
		double amount = 10.00;
		dto.setAmount(20.00);
		assertEquals(dto.getAmount(), 20.00);
	}
	
	@Test
	void testDescription() {
		String description = "beer money";
		dto.setDescription("fun money");
		assertEquals(dto.getDescription(), "fun money");
	}
	
//	@Test
////	public void testCreationDate() {
//		/**
//		 * TODO: Test Instant Type?
//		 */
//	}

}
