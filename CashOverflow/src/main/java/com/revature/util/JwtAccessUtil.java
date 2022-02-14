package com.revature.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.model.UserAccount;
import com.revature.service.UserAccountService;

/**
 * Utility class to statically get a username from a token
 * 
 * @author Tyler Rondeau, Luis Estevez, Luis Rivera
 */
public class JwtAccessUtil {

	private JwtAccessUtil() {
	}

	@Autowired
	static JwtUtil util;
	
	@Autowired
	static UserAccountService serv;

	/**
	 * Get the username of the user tied to a given token
	 * 
	 * @param token - token to get username from
	 * @return - String username of user tied to token
	 */
	public static String getUsernameFromToken(String token) {
		return util.getUsernameFromToken(token);
	}
	
	public static UserAccount getUserFromToken(String token) {
		return serv.getUserFromUsername(getUsernameFromToken(token));
	}
}