package com.door.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

public class AuthFilter implements GatewayFilter{
	private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		ServerHttpRequest request = exchange.getRequest();
		
		logger.info("==================");
		logger.info("Once per Request");
		logger.info("==================");
		
		System.out.println("==================");
		System.out.println("Once per Request");
		System.out.println("==================");
		
		//String token = 
		
		return chain.filter(exchange);
	}

}
