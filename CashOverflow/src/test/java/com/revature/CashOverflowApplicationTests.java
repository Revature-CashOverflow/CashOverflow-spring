package com.revature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.controller.LoginController;

import com.revature.service.LoginServiceImpl;

@SpringBootTest
class CashOverflowApplicationTests {
	
	@Autowired
	private LoginController loginController;
	
	@Autowired
	private LoginServiceImpl loginServiceImpl;

	@Test
	void contextLoads() {
		assertThat(loginController).isNotNull();
		assertThat(loginServiceImpl).isNotNull();
	}

<<<<<<< HEAD
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
=======
	
>>>>>>> f60eb5864783b472b01a0907b17ef8cc46f7e88b
}
