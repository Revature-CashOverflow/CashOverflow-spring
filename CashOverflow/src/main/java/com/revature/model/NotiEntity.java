package com.revature.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author rasco
 *
 */
@Entity
@Table(name = "noti_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotiEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	Account account;
	
	@OneToOne
	@JoinColumn(name = "tx_id")
	Transaction transaction;
}
