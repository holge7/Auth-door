package com.door.utils;

import java.util.ArrayList;

public class ApiResponse {
	
	String message;
	Boolean error;
	Object data;
	
	public ApiResponse() {}
	
	public ApiResponse(String message, Boolean error, Object data) {
		this.message = message;
		this.error = error;
		this.data = data;
	}

	public ApiResponse(String message, Boolean error) {
		this.message = message;
		this.error = error;
		this.data = null;
	}

	public ApiResponse(String message) {
		this.message = message;
		this.error = true;
		this.data = new ArrayList<>();
	}
	
	public ApiResponse(Object data) {
		this.message = "";
		this.error = false;
		this.data = data;
	}

	public ApiResponse(Boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ApiResponse [message=" + message + ", error=" + error + ", data=" + data + "]";
	}
	
	
	
}