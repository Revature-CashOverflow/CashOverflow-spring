package com.revature.controller;

import static org.junit.Assert.assertEquals;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.revature.dto.SettingsDto;
import com.revature.service.SettingsService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SettingsControllerTest {
	
	@Mock
	private SettingsController settingsController;
	
	@Mock
	private SettingsService settingsServ;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	public void testSettingsController() {
		
		settingsController = new SettingsController(settingsServ, encoder);
		
		SettingsDto settingsDto = new SettingsDto("mbaileyfuturist", "12!@QW44");
		
		boolean success = false;
		
		assertEquals(success, settingsController.changePassword(settingsDto));
		

	}

}