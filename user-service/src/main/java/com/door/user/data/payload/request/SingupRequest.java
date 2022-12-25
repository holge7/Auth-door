package com.door.user.data.payload.request;

import java.util.Set;

import com.door.user.entity.Rol;

public class SingupRequest {
	
	public String username;
	public String email;
	public Set<Rol> role;
	public String password;

	public SingupRequest() {}

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

	public Set<Rol> getRole() {
		return role;
	}

	public void setRole(Set<Rol> role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
