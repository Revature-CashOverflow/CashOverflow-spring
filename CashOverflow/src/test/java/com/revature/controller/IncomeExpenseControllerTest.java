package com.revature.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.revature.dto.TransactionDto;
import com.revature.service.TransactionService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class IncomeExpenseControllerTest {
	
	@Mock
	private static TransactionService txServ;
	
	private static IncomeExpenseController lou;
	
	@BeforeAll
	static void init() {
		lou = new IncomeExpenseController(txServ);
	}

	@Test
	void negativeTransactionTest() {
		TransactionDto dto = new TransactionDto(1, 1, -100.00, "SOS", Instant.now());
		
		assertThrows(ResponseStatusException.class, () -> lou.addTransaction(dto));
	}

	@Test
	void badRequestTest() {
		TransactionDto dto = new TransactionDto(0, 2, 1.00, "", Instant.now());
		assertThrows(ResponseStatusException.class, () -> lou.addTransaction(dto));
		TransactionDto dto1 = new TransactionDto(1, 2, null, "", Instant.now());
		assertThrows(ResponseStatusException.class, () -> lou.addTransaction(dto1));
		TransactionDto dto2 = new TransactionDto(1, 2, 1.00, null, Instant.now());
		assertThrows(ResponseStatusException.class, () -> lou.addTransaction(dto2));
		
	}
}
