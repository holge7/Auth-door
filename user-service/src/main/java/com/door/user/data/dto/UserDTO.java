package com.door.user.data.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.door.user.entity.Rol;

public class UserDTO {
	public String email;
	public String name;
	public List<String> rol;
	
	public UserDTO() {}
	
	public UserDTO(String email, String name, Set<Rol> rol) {
		this.email = email;
		this.name = name;
		this.rol = extractRoles(rol);
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
	
	public void setRol(Set<Rol> rol) {
		this.rol = extractRoles(rol);
	}
	
	public List<String> getRol() {
		return this.rol;
	}
	
	private List<String> extractRoles(Set<Rol> rol) {
		List<String> roles = new ArrayList<>();
		
		for (Rol r : rol) {
			roles.add(r.getRol().toString());
		}
		
		return roles;
	}

	@Override
	public String toString() {
		return "UserDTO [email=" + getEmail() + ", name=" + getName() + ", rol=" + getRol() + "]";
	}
	
}
