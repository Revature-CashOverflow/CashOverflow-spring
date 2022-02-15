package com.revature.controller;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.revature.service.BankAccountService;
import com.revature.service.UserAccountService;

@SpringBootTest
@Transactional
@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
	
	@Mock
	private BankAccountService bServ;
	
	private UserAccountService uServ;
	
//	@Mock
//	private BankAccountRepo dao;
	
	private AccountController aCont;
	
	private ModelMapper map;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		aCont = new AccountController(bServ, map, uServ);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	/*
	@Test
	void createBankAccountTest() {
		UserAccount initialTestUser = new UserAccount(
				1000,
				"testuseremail@emailprovider.com",
				"testUserUsername",
				"testUserFirstName",
				"testUserLastName",
				"testUserPassword",
				null
				);
		BankAccount initialBankAccount = new BankAccount(
				"cbaTest",
				"cbaTest description",
				Instant.now(),
				1,
				initialTestUser);
		BankAccountDto initialBankAccountDto = new BankAccountDto(initialBankAccount);
		
		String token = "nyaa~";
		
		
		when(serv.createAccount(initialBankAccount)).thenReturn(initialBankAccount);
		aCont.createBankAccount(initialBankAccountDto, token);
		verify(serv, times(1)).createAccount(initialBankAccount);
		
		assertEquals(initialBankAccount.getName(), initialBankAccount.getName());
		
		
//		fail("Not yet implemented");
	}
	*/

}
