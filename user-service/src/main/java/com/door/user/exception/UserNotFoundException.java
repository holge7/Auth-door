package com.door.user.exception;

import org.springframework.http.HttpStatus;

import com.door.user.entity.User;

public class UserNotFoundException extends UserException {

	private static final long serialVersionUID = 1L;
	private static String msgString = RESOURCE_NAME+" [%s] not found.";
	private static HttpStatus httpStatus = HttpStatus.NOT_FOUND;

	public UserNotFoundException(User user) {
		super(msg(user), httpStatus);
	}
	
	
	public UserNotFoundException(String user) {
		super(msg(user), httpStatus);
	}
	
	private static String msg(String user) {
		return String.format(msgString, user);		
	}
	
	private static String msg(User user) {
		return String.format(msgString, user.getEmail());		
	}

}
