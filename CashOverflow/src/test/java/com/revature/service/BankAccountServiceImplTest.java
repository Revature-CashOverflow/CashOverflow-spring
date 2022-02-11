package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import com.revature.dao.BankAccountRepo;
import com.revature.model.BankAccount;
import com.revature.model.UserAccount;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BankAccountServiceImplTest {

	@Mock
	private BankAccountRepo dao;
	
	private BankAccountService serv;
	
	@BeforeEach
	void setUp() throws Exception {
		serv = new BankAccountServiceImpl(dao);
	}
	
	/**
	 * This method tests the BankAccountService method createAccount.
	 * It should return a BankAccount object with balance set to zero.
	 */
	@Test
	void createAccountTest() {
		UserAccount initialTestUser = new UserAccount(
				1000,
				"testuseremail@emailprovider.com",
				"testUserUsername",
				"testUserFirstName",
				"testUserLastName",
				"testUserPassword",
				null
				);
		
		BankAccount initialTestBankAccount = new BankAccount(
				"myBankAccountName",
				"myBankAccountDescription",
				null,
				1,
				initialTestUser);
		initialTestBankAccount.setBalance(2000000.0);
		
		UserAccount expectedTestUser = new UserAccount(
				1000,
				"testuseremail@emailprovider.com",
				"testUserUsername",
				"testUserFirstName",
				"testUserLastName",
				"testUserPassword",
				null
				);
		
		BankAccount expectedTestBankAccount = new BankAccount(
				"myBankAccountName",
				"myBankAccountDescription",
				null,
				1,
				expectedTestUser);
		expectedTestBankAccount.setBalance(0.0);

		when(dao.save(initialTestBankAccount)).thenReturn(initialTestBankAccount);
		System.out.println("initia: "+initialTestBankAccount);
		BankAccount test = serv.createAccount(initialTestBankAccount);
		System.out.println("test: "+ test);
		verify(dao, times(1)).save(initialTestBankAccount);
		assertEquals(test, initialTestBankAccount);
		
		
		
	}
	
	/**
	 * This method tests the BankAccountService method getBankAccounts.
	 * This method should be returning a list of BankAccounts
	 */
	@Test
	void selectAllBankAccountsByUser() {
		
		UserAccount initialTestUser = new UserAccount(
				1,
				"testuseremail@emailprovider.com",
				"testUserUsername",
				"testUserFirstName",
				"testUserLastName",
				"testUserPassword",
				Instant.now()
				);
		
		BankAccount initialTestBankAccount = new BankAccount(
				"myBankAccountName",
				"myBankAccountDescription",
				Instant.now(),
				1,
				initialTestUser);
		
		List<BankAccount> initialList = new ArrayList<>();
		initialList.add(initialTestBankAccount);
		List<BankAccount> expectedList = new ArrayList<>();
		expectedList.addAll(initialList);
		when(dao.findAllByUserId(1)).thenReturn(initialList);
		
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.setParameter("id", "1");
		
		List<BankAccount> test = serv.getBankAccounts(req);
		System.out.println("test list: "+test);
		
		verify(dao, times(1)).findAllByUserId(initialTestBankAccount.getUser().getId());
		assertEquals(expectedList, test);
	}
}
