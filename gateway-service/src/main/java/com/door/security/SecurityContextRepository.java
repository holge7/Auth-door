package com.door.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.door.jwt.JwtUtils;
import com.google.common.net.HttpHeaders;

import reactor.core.publisher.Mono;

@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {
	
	@Autowired
	public CustomAuthenticationManager customAuthenticationManager;
	
	@Autowired
	public JwtUtils jwtUtils;
	
	@Override
    public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange swe) {
        //Load token from Authorization header and pass it along so we can use it for authentication
        ServerHttpRequest request = swe.getRequest();
        String jwt = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            Authentication auth = new UsernamePasswordAuthenticationToken(jwt, jwt);
            return this.customAuthenticationManager.authenticate(auth)
                    .map(SecurityContextImpl::new);
        } else {
            return Mono.empty();
        }
    }

}
