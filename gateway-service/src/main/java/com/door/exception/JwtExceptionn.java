package com.door.exception;

import org.springframework.http.HttpStatus;


public class JwtExceptionn extends RuntimeException {

	private static final long serialVersionUID = 1L;

	
	protected String msg;
	protected HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
	
	public JwtExceptionn(Exception e) {
		this.msg = e.toString();
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
		return "JwtException [msg=" + msg + ", httpStatus=" + httpStatus + "]";
	}

	
	
}
