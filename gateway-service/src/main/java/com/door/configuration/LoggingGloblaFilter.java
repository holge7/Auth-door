package com.door.configuration;

import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import javax.print.event.PrintJobAttributeEvent;

import org.slf4j.Logger;

import reactor.core.publisher.Mono;

import com.door.jwt.JwtUtils;

@Component
public class LoggingGloblaFilter implements GlobalFilter {
	final Logger logger = LoggerFactory.getLogger(LoggingGloblaFilter.class);
	
	public JwtUtils jwtUtils;
	
	public LoggingGloblaFilter(JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("=====================================");
		logger.info("Global Pre Filter executed");
		logger.info("=====================================");
		ServerHttpRequest request = exchange.getRequest();
		
		
		return chain.filter(exchange);
	}
	

	private String parseJwt(HttpServletRequest request) {
		// Get header for auth
		String headerAuth = request.getHeader("Authorization");
		
		// Check if it have text and is of type Bearer
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length()); // substring of 7 becouse it is "|B|e|a|r|e|r| | ......."
		}
		
		return null;
	}

}
