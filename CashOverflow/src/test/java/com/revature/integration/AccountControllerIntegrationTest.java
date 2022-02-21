package com.revature.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.CashOverflowApplication;
import com.revature.dao.BankAccountRepo;
import com.revature.dao.UserAccountRepo;
import com.revature.dto.BankAccountDto;
import com.revature.model.BankAccount;
import com.revature.model.UserAccount;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CashOverflowApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AccountControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private BankAccountRepo bankRepo;
	
	@Autowired
	private UserAccountRepo userRepo;
	
	@Autowired
	private PasswordEncoder enc;
	
	@BeforeEach
	void setUp() {
		bankRepo.deleteAll();
		userRepo.deleteAll();
		
		UserAccount user = new UserAccount(0, "user1@gmail.com", "user1", "user", "1", enc.encode("user1"), Instant.now());
		userRepo.save(user);
		user = userRepo.findByUsername("user1");
		
		bankRepo.save(new BankAccount(0, "name1", 0.0, "description1", Instant.now(), 1, user, null));
		bankRepo.save(new BankAccount(0, "name2", 0.0, "description2", Instant.now(), 1, user, null));
		bankRepo.save(new BankAccount(0, "name3", 0.0, "description3", Instant.now(), 2, user, null));
	}
	
	@Test
	@WithMockUser(value = "user1")
	void testNullAccountName() throws Exception {
		BankAccountDto dto = new BankAccountDto();
		dto.setDescription("description");
		dto.setName(null);
		dto.setAccountTypeId(1);
		
		mvc.perform(post("/api/account/createBankAccount").content(mapper.writeValueAsString(dto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
	
	@Test
	@WithMockUser("user1")
	void testBlankAccountName() throws Exception {
		BankAccountDto dto = new BankAccountDto();
		dto.setDescription("description");
		dto.setName("");
		dto.setAccountTypeId(1);
		
		mvc.perform(post("/api/account/createBankAccount").content(mapper.writeValueAsString(dto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
	
	@Test
	@WithMockUser("user1")
	void testCreateAccountSuccess() throws Exception {
		BankAccountDto dto = new BankAccountDto();
		dto.setDescription("description");
		dto.setName("name");
		dto.setAccountTypeId(1);
		
		mvc.perform(post("/api/account/createBankAccount").content(mapper.writeValueAsString(dto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}
	
	@Test
	@WithMockUser("user1")
	void testGetBankAccounts() throws Exception {
		MvcResult result = mvc.perform(get("/api/account/getBankAccounts"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(authenticated().withAuthenticationName("user1"))
			.andReturn();
		
		String responseBody = result.getResponse().getContentAsString();
		List<BankAccountDto> dtos = mapper.readValue(responseBody, new TypeReference<List<BankAccountDto>>() {});
		
		assertEquals(dtos.size(), 3);
	}
}
