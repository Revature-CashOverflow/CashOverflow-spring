package com.revature.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.CashOverflowApplication;
import com.revature.dao.BankAccountRepo;
import com.revature.dto.BankAccountDto;
import com.revature.helper.TestHelper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CashOverflowApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AccountControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private BankAccountRepo repo;
	
	@Test
	@WithMockUser("user")
	void testNullAccountInput() throws Exception {
		BankAccountDto dto = new BankAccountDto();
		dto.setDescription("description");
		dto.setName(null);
		dto.setAccountTypeId(1);
		
		mvc.perform(post("/api/account/createBankAccount").content(TestHelper.asJsonString(dto, mapper))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}
}
