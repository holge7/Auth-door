package com.door.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.door.jwt.JwtData;
import com.door.jwt.JwtUtils;
import com.google.gson.Gson;

import reactor.core.publisher.Mono;

@Component
public class CustomAuthenticationManager implements ReactiveAuthenticationManager {
	
	@Autowired
	JwtUtils jwtUtil;

	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		
		System.out.println("=====================");
		System.out.println("CUSTOM AUTH MANAGER");
		System.out.println("=====================");
		String authToken = authentication.getCredentials().toString();
        try {
            var claims = jwtUtil.getAllClaimsFromToken(authToken);
            
            JwtData jwtData = new Gson().fromJson(claims.getBody().getSubject(), JwtData.class);
            
            //Get list of roles for this user
            //ArrayList<String> perms = (ArrayList<String>) claims.getBody().get("authorities");
            ArrayList<String> perms = new ArrayList<>();
            perms.add("ROLE_USER");
            var authorities = perms.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

            return Mono.just(new UsernamePasswordAuthenticationToken(claims.getBody().getSubject(), null, authorities));
        } catch (Exception e) {
            return Mono.empty();
        }
	}

}
