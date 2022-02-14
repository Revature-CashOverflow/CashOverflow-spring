package com.revature.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class LoginUserAccountDto {

	Integer id;
	String username;
	String firstName;
	String lastName;
	Instant creationDate;

}