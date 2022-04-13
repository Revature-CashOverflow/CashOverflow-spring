package com.revature.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BetweenUsers {

	int sendOrReceive;
	String username;
	String transferAccount;
	Double transferAmount;
}
