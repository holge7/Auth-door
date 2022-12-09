package com.door.auth.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.door.auth.data.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
	
}
