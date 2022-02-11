package com.revature.util;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Utility class to statically get a username from a token
 * @author Tyler Rondeau, Luis Estevez, Luis Rivera
 */
public class JwtAccessUtil {

	@Autowired
	static JwtUtil util;
	
	/**
	 * Get the username of the user tied to a given token
	 * @param token - token to get username from
	 * @return - String username of user tied to token
	 */
	public static String getUsernameFromToken(String token) {
		return util.getUsernameFromToken(token);
	}
}