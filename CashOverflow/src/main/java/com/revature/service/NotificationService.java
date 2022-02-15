package com.revature.service;

import java.util.List;

import com.revature.model.Notification;

public interface NotificationService {
	List<Notification> findNotificationsByUserId(Integer id);
}
