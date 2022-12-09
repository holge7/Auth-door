package com.door.configuration;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.stax2.io.Stax2URLSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

import com.door.auth.ERol;
import com.door.security.CustomAuthenticationManager;
import com.door.security.SecurityContextRepository;
import com.door.security.ServerAuthenticationEntryPointImpl;

import jakarta.ws.rs.HttpMethod;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class HelloWebfluxSecurityConfig{
	
	//@Autowired
	//ServerAuthenticationEntryPointImpl authFilter;
	
	//@Autowired
	//LogginFilter logginFilter;
	
	@Autowired
	SecurityContextRepository securityContextRepository;
	
	@Autowired
	CustomAuthenticationManager customAuthenticationManager;
	
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
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() ->
                        swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)
                )).accessDeniedHandler((swe, e) -> Mono.fromRunnable(() ->
                        swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))).and()
                .authorizeExchange()
                .pathMatchers(Routes.PUBLIC_ROUTES).permitAll()
                .pathMatchers(Routes.USER_ROUTES).hasRole("ADMIN")
                .and().build();
    }

    /*@Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    	List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        http.csrf().disable()
        .authenticationManager(customAuthenticationManager)
        .addFilterAfter(logginFilter, SecurityWebFiltersOrder.AUTHENTICATION)
        .exceptionHandling().authenticationEntryPoint(authFilter)
        .and()
        .authorizeExchange()
        .pathMatchers(Routes.PUBLIC_ROUTES).permitAll() //public routes
        .pathMatchers(Routes.USER_ROUTES).hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
 
        return http.build();
    }*/
    
	
}
