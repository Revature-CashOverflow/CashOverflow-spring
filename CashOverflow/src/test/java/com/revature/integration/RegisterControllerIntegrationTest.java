package com.revature.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
	void testGetWrongMethod() throws Exception {
		mvc.perform(get("/register")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	void testNullValue() throws Exception {
		mvc.perform(post("/login").content(TestHelper.asJsonString(new RegUserAccountDto("email", "username", "first", "last", null), mapper))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}
}
