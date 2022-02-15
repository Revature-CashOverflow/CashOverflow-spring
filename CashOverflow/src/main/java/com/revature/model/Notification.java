package com.revature.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model for notifications
 * @author Colin Knox, Parker Mace, Tyler Rondeau
 */
@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	Instant creationDate;
	
	NotiMessage notiMessage;
	
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	NotiEntity entity;
	
	Integer userId;
}
