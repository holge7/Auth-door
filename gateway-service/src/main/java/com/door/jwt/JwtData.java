package com.door.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtData {
	
	String email;
	String roles; //TODO: ESTO TIENE QUE SER UN LIST<STRING>
	
	public JwtData() {}

	public JwtData(String email, String roles) {
		this.email = email;
		this.roles = roles;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "JwtData [email=" + email + ", roles=" + roles + "]";
	}
	
	
	
}
