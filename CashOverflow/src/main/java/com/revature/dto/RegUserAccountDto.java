package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegUserAccountDto {
	
	String email;
	String username;
	String firstName;
	String lastName;
	String password;

}
