package com.door.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Configuration
public class ServerAuthenticationEntryPointImpl implements ServerAuthenticationEntryPoint{
	Log logger = LogFactory.getLog(getClass());

	@Override
	public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
	
		System.out.println("===================");
		System.out.println("Auth Exception");
		System.out.println("===================");
		logger.error(ex);
		
		return null;
	}

}
