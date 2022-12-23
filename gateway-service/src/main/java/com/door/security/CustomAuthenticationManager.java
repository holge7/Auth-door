package com.door.security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.door.jwt.JwtData;
import com.door.jwt.JwtUtils;

import reactor.core.publisher.Mono;

/**
 * Authentication class using JWT and system based on ROLES
 * @author Holge
 *
 */
@Component
public class CustomAuthenticationManager implements ReactiveAuthenticationManager {
	
	@Autowired
	JwtUtils jwtUtil;

	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		
		// Get User
		JwtData user= (JwtData) authentication.getPrincipal();

        // Set authorities of the User
        var authorities = user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        // Return a authentication with user and authorities
        return Mono.just(new UsernamePasswordAuthenticationToken(user, null, authorities));
		
	}

}
