package com.revature.model;

/**
 * 
 * @author rasco
 *
 */
public enum NotiMessage {
	
	TEST_MSG("test noti");
	
	private String message;
	
	private NotiMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
