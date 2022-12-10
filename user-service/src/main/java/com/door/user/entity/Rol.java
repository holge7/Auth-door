package com.door.user.entity;

import com.door.user.data.ERol;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@Enumerated(EnumType.STRING)
	public ERol rol;
	
	public Rol() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ERol getRol() {
		return rol;
	}

	public void setRol(ERol rol) {
		this.rol = rol;
	}
	
}
