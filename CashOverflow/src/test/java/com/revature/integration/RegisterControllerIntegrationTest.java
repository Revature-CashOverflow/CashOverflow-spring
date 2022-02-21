package com.revature.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.CashOverflowApplication;
import com.revature.dao.UserAccountRepo;
import com.revature.dto.RegUserAccountDto;
import com.revature.helper.TestHelper;

/**
 * Integration tests for the register controller
 * 
 * @author Colin Knox
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CashOverflowApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RegisterControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private UserAccountRepo repo;

	@Autowired
	private PasswordEncoder enc;

	@BeforeEach
	void setUpEach() {
		repo.deleteAll();
	}

	@Test
	void testWrongMethod() throws Exception {
		mvc.perform(get("/register")).andExpect(status().isMethodNotAllowed());
		
		mvc.perform(put("/register")).andExpect(status().isMethodNotAllowed());
		
		mvc.perform(delete("/register")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	void testNullInputValue() throws Exception {
		mvc.perform(post("/register").content(TestHelper.asJsonString(new RegUserAccountDto(null, "username", "first", "last", "password"), mapper))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		
		mvc.perform(post("/register").content(TestHelper.asJsonString(new RegUserAccountDto("email", null, "first", "last", "password"), mapper))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		
		mvc.perform(post("/register").content(TestHelper.asJsonString(new RegUserAccountDto("email", "username", null, "last", "password"), mapper))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		
		mvc.perform(post("/register").content(TestHelper.asJsonString(new RegUserAccountDto("email", "username", "first", null, "password"), mapper))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		
		mvc.perform(post("/register").content(TestHelper.asJsonString(new RegUserAccountDto("email", "username", "first", "last", null), mapper))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
}
