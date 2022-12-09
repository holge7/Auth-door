package com.door.auth.exception;

import org.springframework.http.HttpStatus;

public class UserRegisterNotValidException extends UserException{

	private static final long serialVersionUID = 1L;
	private static String msgString = "Email or password incorrect.";
	private static HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

	public UserRegisterNotValidException() {
		super(msgString, httpStatus);
	}

}
