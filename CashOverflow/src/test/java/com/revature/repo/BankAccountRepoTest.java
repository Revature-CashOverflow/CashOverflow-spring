package com.revature.repo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dao.BankAccountRepo;


@SpringBootTest
@Transactional
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
	 * This method tests the BankAccountRepo method findAllByUserId. This method
	 * should be returning a list of BankAccounts associated with a UserAccount.
	 * Note: Need to comfirm if return can be null
	 * 
	 */
//	@Test
//	void findAllByUserIdTest() {
//
//		List<BankAccount> test = dao.findAllByUserId(2);
//		assertTrue(test.size() >= 1);
//
//	}
	/**
	 * This method tests the BankAccountRepo method save. This method
	 * should be returning a BankAccount with the same information as the one saved.
	 */
//	@Test
//	@Rollback(true)
//	void saveTest() {
//
//		UserAccount initialTestUser = new UserAccount(1, "testuseremail@emailprovider.com", "testUserUsername",
//				"testUserFirstName", "testUserLastName", "testUserPassword", null);
//		BankAccount initialTestBankAccount = new BankAccount("myBankAccountTEST", "myBankAccountDescription2",
//				Instant.now(), 1, initialTestUser);
//		initialTestBankAccount.setBalance(0.0);
//
//		BankAccount test = dao.save(initialTestBankAccount);
//		assertEquals(test.getName(), initialTestBankAccount.getName());
//
//	}

}
