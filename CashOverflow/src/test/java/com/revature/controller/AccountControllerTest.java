package com.revature.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dao.BankAccountRepo;
import com.revature.dto.BankAccountDto;
import com.revature.model.BankAccount;
import com.revature.model.UserAccount;
import com.revature.service.BankAccountService;
import com.revature.util.JwtAccessUtil;

@SpringBootTest
@Transactional
@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
	
	@Mock
	private BankAccountService serv;
	
//	@Mock
//	private BankAccountRepo dao;
	
	private AccountController aCont;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		aCont = new AccountController(serv);
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
