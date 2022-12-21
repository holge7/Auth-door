package com.door.loginservice.dto;

import java.util.List;

public class JwtResponse {
	
	public String token;
	public String type = "Bearer";
	public String username;
	public String email;
	public List<String> roles;

	public JwtResponse() {}

	public JwtResponse(String token, String username, String email, List<String> roles) {
		this.token = token;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "JwtResponse [token=" + token + ", type=" + type + ", username=" + username + ", email=" + email
				+ ", roles=" + roles + "]";
	}
	
}
