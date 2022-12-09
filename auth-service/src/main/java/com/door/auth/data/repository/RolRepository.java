package com.door.auth.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.door.auth.data.ERol;
import com.door.auth.data.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{
	
	public Optional<Rol> findByRol(ERol rol);
	
}
