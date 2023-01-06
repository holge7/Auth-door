package com.door.loginservice.dto;

import java.util.List;

public class JwtResponse {
	
	public String token;
	public String type = "Bearer";
	public String username;
	public String email;
	public List<String> rol;

	public JwtResponse() {}

	public JwtResponse(String token, String username, String email, List<String> rol) {
		this.token = token;
		this.username = username;
		this.email = email;
		this.rol = rol;
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

	public List<String> getRol() {
		return rol;
	}

	public void setRol(List<String> rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "JwtResponse [token=" + token + ", type=" + type + ", username=" + username + ", email=" + email
				+ ", rol=" + rol + "]";
	}
	
}
