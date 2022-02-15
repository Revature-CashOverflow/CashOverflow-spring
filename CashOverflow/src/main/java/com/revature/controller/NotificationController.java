package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Notification;
import com.revature.service.NotificationService;
import com.revature.service.UserAccountService;

@RestController
@RequestMapping("/api")
public class NotificationController {

	NotificationService notiServ;
	UserAccountService userServ;

	@Autowired
	public NotificationController(NotificationService notiServ, UserAccountService userServ) {
		this.notiServ = notiServ;
		this.userServ = userServ;
	}

	@GetMapping("/notifications/user")
	public List<Notification> getUserNotifications(Authentication auth) {
		return notiServ.findNotificationsByUserId(userServ.getUserFromUsername(auth.getName()).getId());
	}
}
