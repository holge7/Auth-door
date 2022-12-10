package com.door.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.door.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
	
}
