package com.revature.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;

import com.revature.dto.TransactionDto;
import com.revature.model.Transaction;
import com.revature.service.RegisterServiceImpl;
import com.revature.service.TransactionService;
import com.revature.service.TransactionServiceImpl;
import com.revature.dao.TransactionRepo;
import com.revature.controller.IncomeExpenseController;

/**
 * Tests Income Expense Controller  
 * This test case is still in PROGRESS
 * @author Cameron, Amir, Chandra
 *
 */

// annotation parameter added by Chandra
@SpringBootTest (classes = {IncomeExpenseControllerTest.class})
@ExtendWith(MockitoExtension.class)
class IncomeExpenseControllerTest {

	private TransactionServiceImpl regServ;
	
	private IncomeExpenseController ieCont;

	@Mock
	private TransactionRepo mockTransRepo;

	@Mock
	private TransactionService transServ;

	@Mock
	private ModelMapper transMapper;


	@Mock
	private Authentication tauth;
	
	
	@BeforeEach
	public void setUp() {
		transServ = new TransactionServiceImpl(mockTransRepo, transMapper);
		ieCont = new IncomeExpenseController(transServ);
	}

	
	
//	@Test
//	void serviceTest() {
//		UserAccount test = new UserAccount(3, "email@gmail.com", "username", "firstname", "lastname", "password", null);
//		when(mockRepo.save(test)).thenReturn(test);
//		UserAccount result = transServ.insertUserAccount(test);
//		verify(mockRepo, times(1)).save(test);
//		assertNotNull(result.getCreationDate());
//	}
	
	
	
	@Test
	void IncomeExpenseControllerTest() {
		TransactionDto tDto = new TransactionDto(38, 1, 30.0, "test1", "reimbursement for test");
				
		Transaction trans = new Transaction();
		trans.setId(tDto.getAccountId());
		trans.setAmount(tDto.getAmount());
		trans.setName(tDto.getName());
		trans.setDescription(tDto.getDescription());

		when(transMapper.map(tDto, Transaction.class)).thenReturn(trans);
		ieCont.addTransaction(tauth, tDto);


		verify(transServ, times(1)).addTransaction(tDto);
//		verify(transMapper, times(1)).map(trans, Transaction.class);

	}

//	@Test
//	void IncomeExpenseControlerTestMissingArg() {
//		RegUserAccountDto test = new RegUserAccountDto(null, "username", "firstname", "lastname", "password");
//    
//		UserAccount user = new UserAccount();
//		user.setEmail(test.getEmail());
//		user.setUsername(test.getUsername());
//		user.setFirstName(test.getFirstName());
//		user.setLastName(test.getLastName());
//		user.setPassword(test.getPassword());
//
//		ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
//			regCont.newUser(test);
//		});
//
//		String expectedReason = "Missing registration info";
//		HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
//		Integer expectedCode = 400;
//		assertEquals(expectedReason, e.getReason());
//		assertEquals(expectedStatus, e.getStatus());
//		assertEquals(expectedCode, e.getRawStatusCode());
//
//		verify(mockServ, times(0)).insertUserAccount(user);
//		verify(mockMapper, times(0)).map(test, UserAccount.class);
//
//	}
}
