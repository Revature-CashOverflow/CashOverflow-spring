package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * Dto object that creates a buffer between input data and the database
 * @author Cameron, Amir, Chandra
 */
@Data
@AllArgsConstructor
public class TransactionDto {
	
	private int id;
	private int txType;
	private Double amount;
	private String name;
	private String description;
	
	

}
