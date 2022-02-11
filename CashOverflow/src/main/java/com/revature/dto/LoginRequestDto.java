package com.revature.dto;

import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.revature.model.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Model for user accounts to send to the client
 * 
 * @author Colin Knox, Parker Mace, Tyler Rondeau
 */
@Data
@Getter
@Setter
@AllArgsConstructor
public class LoginRequestDto {

	String username;
	String password;
}