package com.revature.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

 /**
  * 
  * 
  * Testing getters/setter for the BetweenUserDto
  *
  */

public class BetweenUsersDtoTest {
	
	@Mock
	BetweenUsersDto bdto = new BetweenUsersDto();
	
	@Test
	public void testId() {
		int id = 2;
		bdto.setId(3);
		assertEquals(bdto.getId(), 3);
	}
	
	@Test
	public void testSendOrReceive() {
		int sendOrReceive = 1;
		bdto.setSendOrReceive(0);
		assertEquals(bdto.getSendOrReceive(), 0);
	}
	
	@Test
	public void testUser() {
		String user = "WeezyFBaby";
		bdto.setUser("Wayne");
		assertEquals(bdto.getUser(), "Wayne");
	}
	
	@Test
	public void testOriginUser() {
		String originUser = "Jim";
		bdto.setOriginUser("Jimmy");
		assertEquals(bdto.getOriginUser(), "Jimmy");
	}
	
	@Test
	public void testReceiveAccount() {
		int receiveAccount = 1;
		bdto.setReceiveAccount(2);
		assertEquals(bdto.getReceiveAccount(), 2);
	}
	
	@Test
	public void testTransferAccount() {
		int transferAccount = 12;
		bdto.setTransferAccount(14);
		assertEquals(bdto.getTransferAccount(), 14);
	}
	
	@Test
	public void testTransferAmount() {
		double transferAmount = 100.00;
		bdto.setTransferAmount(50.00);
		assertEquals(bdto.getTransferAmount(), 50.00);
	}

}
