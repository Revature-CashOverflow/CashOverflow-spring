package com.revature.dto;

import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.revature.model.BankAccount;

import lombok.Data;

/**
 * Bank account model that we will return to the client
 * 
 * @author Parker Mace
 */
@Data
public class BankAccountDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	Double balance;
	String description;
	Instant creationDate;
	Integer accountTypeId;

	public BankAccountDto(BankAccount account) {
		this.id = account.getId();
		this.name = account.getName();
		this.balance = account.getBalance();
		this.description = account.getDescription();
		this.creationDate = account.getCreationDate();
		this.accountTypeId = account.getAccountTypeId();
	}
}