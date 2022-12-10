package com.door.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

import com.door.security.CustomAuthenticationManager;
import com.door.security.SecurityContextRepository;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class WebfluxSecurityConfig{
	
	SecurityContextRepository securityContextRepository;
	CustomAuthenticationManager customAuthenticationManager;
	
	public WebfluxSecurityConfig(SecurityContextRepository securityContextRepository, CustomAuthenticationManager customAuthenticationManager) {
		this.customAuthenticationManager = customAuthenticationManager;
		this.securityContextRepository = securityContextRepository;
	}
	
	 
	
	@Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
		
        // Disable default security.
        return http.httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .logout().disable()
                //No session
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                // Add custom security.
                .authenticationManager(this.customAuthenticationManager)
                .securityContextRepository(this.securityContextRepository)
                // Exceptions
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() ->
                        swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)
                )).accessDeniedHandler((swe, e) -> Mono.fromRunnable(() ->
                        swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN)))
                .and()
                // Access to routes
                .authorizeExchange()
                .pathMatchers(Routes.PUBLIC_ROUTES).permitAll()
                .pathMatchers(Routes.USER_ROUTES).hasAnyRole("USER", "MODERATOR", "ADMIN")
                .pathMatchers(Routes.MODERATOR_ROUTES).hasAnyRole("MODERATOR", "ADMIN")
                .pathMatchers(Routes.ADMIN_ROUTES).hasRole("ADMIN")
                .and().build();
    }
	
}
