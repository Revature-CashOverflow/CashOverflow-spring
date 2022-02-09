package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.NotificationRepo;

@Service
public class NotificationServiceImpl implements NotificationService {
	private NotificationRepo notiRepo;

	@Autowired
	public void setUserRepo(NotificationRepo notiRepo) {
		this.notiRepo = notiRepo;
	}
}
