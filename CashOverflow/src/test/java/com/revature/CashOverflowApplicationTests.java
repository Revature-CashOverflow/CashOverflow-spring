package com.revature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.controller.LoginController;
<<<<<<< HEAD
=======
import com.revature.service.LoginServiceImpl;
>>>>>>> 7f9a884147a16615a4ae2ffab8d6661297c1616e

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

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}
