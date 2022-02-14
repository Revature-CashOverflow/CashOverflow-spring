package com.revature.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.dao.BankAccountRepo;
import com.revature.model.BankAccount;
import com.revature.model.UserAccount;

@SpringBootTest
class BankAccountRepoTest {

	@Autowired
	private BankAccountRepo dao;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * This method tests the BankAccountRepo method findAllByUserId.
	 * This method should be returning a list of BankAccounts associated
	 * with a UserAccount.
	 * Note: Need to comfirm if return can be null
	 * 
	 */
	@Test
	void findAllByUserIdTest() {
		
		List<BankAccount> test = dao.findAllByUserId(1);
		assertTrue(test.size() >= 1);
		
	}
	
	@Test
	void saveTest() {
		UserAccount initialTestUser = new UserAccount(
				1,
				"testuseremail@emailprovider.com",
				"testUserUsername",
				"testUserFirstName",
				"testUserLastName",
				"testUserPassword",
				null
				);
		
		BankAccount initialTestBankAccount = new BankAccount(
				"myBankAccountName2",
				"myBankAccountDescription2",
				Instant.now(),
				1,
				initialTestUser);
		initialTestBankAccount.setBalance(0.0);
		
		BankAccount test = dao.save(initialTestBankAccount);
		assertEquals(test.getName(), initialTestBankAccount.getName());
		dao.delete(test);
		
	}

}
