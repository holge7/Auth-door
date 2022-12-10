package com.door.user.data.payload.request;

public class LoginRequest {
	
	public String password;
	public String email;
	
	public LoginRequest() {}
	
	public LoginRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
