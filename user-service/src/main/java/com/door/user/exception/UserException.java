package com.door.user.exception;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException{
	
	public static final long serialVersionUID = 1L;
	public static final String RESOURCE_NAME = "User";
	
	public String msg;
	public HttpStatus httpStatus;
	
	public UserException(String msg, HttpStatus httpStatus) {
		this.msg = msg;
		this.httpStatus = httpStatus;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public String toString() {
		return "UserException [msg=" + msg + ", httpStatus=" + httpStatus + "]";
	}

}
