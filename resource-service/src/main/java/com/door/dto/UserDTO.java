package com.door.dto;

import java.util.List;

public class UserDTO {
	public String email;
	public String name;
	public List<String> roles;
	
	public UserDTO() {}
	
	public UserDTO(String email, String name, List<String> roles) {
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
	
	public void setRol(List<String> roles) {
		this.roles = roles;
	}
	
	public List<String> getRol() {
		return this.roles;
	}

	@Override
	public String toString() {
		return "UserDTO [email=" + email + ", name=" + name + ", rol=" + roles + "]";
	}
	

	
}
