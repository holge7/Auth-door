package com.door.jwt;

import java.util.List;

/**
 * DTO to store JWT body
 * @author Holge
 *
 */
public class JwtData {
	
	String email;
	String name; 
	List<String> roles;
	
	public JwtData() {}

	public JwtData(String email, String name, List<String> roles) {
		this.email = email;
		this.name = name;
		this.roles = roles;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "JwtData [email=" + email + ", roles=" + roles + "]";
	}
	
	
	
}
