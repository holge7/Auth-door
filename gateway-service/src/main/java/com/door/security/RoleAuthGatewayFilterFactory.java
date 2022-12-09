package com.door.security;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RoleAuthGatewayFilterFactory extends
AbstractGatewayFilterFactory<RoleAuthGatewayFilterFactory.Config> {
	
    public static class Config {
        private String role;
    }
    
    public RoleAuthGatewayFilterFactory() {
        super(Config.class);
    }

	@Override
	public GatewayFilter apply(Config config) {
		System.out.println("============");
		System.out.println("Esto no sale");
		System.out.println("============");
        return (exchange, chain) -> {
            var request = exchange.getRequest();
            // JWTUtil can extract the token from the request, parse it and verify if the given role is available
            //if(!JWTUtil.hasRole(request, config.getRole())){
             if (true) {
            	// seems we miss the auth token
                var response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            return chain.filter(exchange);
        };
	}
	
}
