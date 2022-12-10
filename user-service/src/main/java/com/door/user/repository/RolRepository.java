package com.door.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.door.user.data.ERol;
import com.door.user.entity.Rol;


public interface RolRepository extends JpaRepository<Rol, Long>{
	
	public Optional<Rol> findByRol(ERol rol);
	
}
