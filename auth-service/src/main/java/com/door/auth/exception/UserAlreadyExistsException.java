package com.door.auth.exception;

import org.springframework.http.HttpStatus;

import com.door.auth.data.entity.User;

public class UserAlreadyExistsException extends UserException {
	
	private static final long serialVersionUID = 1L;
	private static String msgString = RESOURCE_NAME+" [%s] already exists.";
	private static HttpStatus httpStatus = HttpStatus.CONFLICT;

	public UserAlreadyExistsException(User user) {
		super(msg(user), httpStatus);
	}
	
	public UserAlreadyExistsException(String user) {
		super(msg(user), httpStatus);
	}
	
	private static String msg(String user) {
		return String.format(msgString, user);		
	}
	
	private static String msg(User user) {
		return String.format(msgString, user.getEmail());		
	}
	
	

}
