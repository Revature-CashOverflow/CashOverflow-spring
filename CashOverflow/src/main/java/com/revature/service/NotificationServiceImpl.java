package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.NotificationRepo;
import com.revature.model.Notification;

@Service
public class NotificationServiceImpl implements NotificationService {

	NotificationRepo notiRepo;

	@Autowired
	public NotificationServiceImpl(NotificationRepo notiRepo) {
		this.notiRepo = notiRepo;
	}

	@Override
	public List<Notification> findNotificationsByUserId(Integer id) {
		return notiRepo.findAllByUserIdOrderByCreationDateDesc(id);
	}
}
