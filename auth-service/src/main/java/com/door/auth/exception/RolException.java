package com.door.auth.exception;

import org.springframework.http.HttpStatus;

public abstract class RolException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	protected static final String RESOURCE_NAME = "Rol";
	
	protected String msg;
	protected HttpStatus httpStatus;
	
	protected RolException(String msg, HttpStatus httpStatus) {
		this.msg = msg;
		this.httpStatus = httpStatus;
	}

	protected String getMsg() {
		return msg;
	}

	protected void setMsg(String msg) {
		this.msg = msg;
	}

	protected HttpStatus getHttpStatus() {
		return httpStatus;
	}

	protected void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public String toString() {
		return "RolException [msg=" + msg + ", httpStatus=" + httpStatus + "]";
	}

	
	
}
