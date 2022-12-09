package com.door.security;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.SerializationUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.door.jwt.JwtUtils;
import com.door.utils.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/*
@Configuration
public class LogginFilter implements WebFilter{
    Log logger = LogFactory.getLog(getClass());
    
    JwtUtils jwtUtils;
    
    public LogginFilter(JwtUtils jwtUtils) {
    	this.jwtUtils = jwtUtils;
    }

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

		System.out.println("================");
		System.out.println("Loggin Filter");
		System.out.println("================");
	
		// Get Authorization Header
		String jwt = exchange.getRequest().getHeaders().getFirst("Authorization");
		
		// User try to access as auth user
		if (jwt!=null) {
			if (jwtUtils.validateJwtToken(jwt)) {
				// If the user is try to enter has auth and it jwt is correct we authenticate he
				System.out.println("Email: "+jwtUtils.getEmailNameFromJwtToken(jwt));
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				
				UserDetailsImpl userDetails = new UserDetailsImpl(1L, "Holge", "jorge@gmail.com", "password", authorities);
				
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						authorities);
								
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
				Authentication authenticationn = SecurityContextHolder.getContext().getAuthentication();
				UserDetailsImpl userLogg = (UserDetailsImpl) authenticationn.getPrincipal();
				System.out.println("User is authenticated");
				System.out.println("=======================");
				System.out.println(userLogg.getEmail());
				System.out.println(userLogg.getUsername());
				System.out.println(userLogg.getAuthorities());
				System.out.println("=======================");
				
			} else {
				//TODO: send ApiResponse with jwt err
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
		        return exchange.getResponse().setComplete();
			}
		}
		
		// jwt valid and user is authenticated or user try to access as anonymous
		return chain.filter(exchange);
	}
	
	public byte[] setBodyResponse(ApiResponse response) {
		// Serialize response
		//return SerializationUtils.serialize(response);
		return "Some text".getBytes(StandardCharsets.UTF_8);
	}
	
}*/