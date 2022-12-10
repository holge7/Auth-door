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
import com.fasterxml.jackson.databind.ObjectMapper;

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
		
		// Get token
		String jwt = authentication.getCredentials().toString();
		
        try {
        	// Decrypt and get all info about the JWT
            var claims = jwtUtil.getAllClaimsFromToken(jwt);
            
            // Parse jwt to JwtData
            ObjectMapper objectMapper = new ObjectMapper();
            JwtData user = objectMapper.readValue(claims.getBody().getSubject(), JwtData.class);

            // Set authorities of the JWT
            var authorities = user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

            // Return a authentication with jwt body and authorities
            return Mono.just(new UsernamePasswordAuthenticationToken(claims.getBody().getSubject(), null, authorities));
        } catch (Exception e) {
        	System.out.println(e);
            return Mono.empty();
        }
	}

}
