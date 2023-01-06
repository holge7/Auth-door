// package com.door.configuration;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
// import org.springframework.security.config.web.server.ServerHttpSecurity;
// import org.springframework.security.web.server.SecurityWebFilterChain;
// import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

// import com.door.security.CustomAuthenticationManager;
// import com.door.security.SecurityContextRepository;
// import com.door.security.UnauthenticateHandler;
// import com.door.security.UnauthorizedHandler;

// @Configuration
// @EnableWebFluxSecurity
// public class WebfluxSecurityConfig{
	
// 	SecurityContextRepository securityContextRepository;
// 	CustomAuthenticationManager customAuthenticationManager;
// 	UnauthorizedHandler unauthorizedHandler;
// 	UnauthenticateHandler unauthenticateHandler;
	
// 	public WebfluxSecurityConfig(
// 			SecurityContextRepository securityContextRepository, 
// 			CustomAuthenticationManager customAuthenticationManager,
// 			UnauthorizedHandler unauthorizedHandler,
// 			UnauthenticateHandler unauthenticateHandler) {
// 		this.customAuthenticationManager = customAuthenticationManager;
// 		this.securityContextRepository = securityContextRepository;
// 		this.unauthorizedHandler = unauthorizedHandler;
// 		this.unauthenticateHandler = unauthenticateHandler;
// 	}
	
// 	@Bean
//     public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
		
//         // Disable default security.
//         return http.httpBasic().disable()
//                 .formLogin().disable()
//                 .csrf().disable()
//                 .logout().disable()
//                 //No session
//                 .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
//                 // Add custom security.
//                 .authenticationManager(this.customAuthenticationManager)
//                 .securityContextRepository(this.securityContextRepository)
//                 // Exceptions
//                 .exceptionHandling()
//                 //.authenticationEntryPoint(this.unauthenticateHandler)
//                 .accessDeniedHandler(this.unauthorizedHandler)
//                 .and()
//                 // Access to routes
//                 .authorizeExchange()
//                 .pathMatchers(Routes.PUBLIC_ROUTES).permitAll()
//                 .pathMatchers(Routes.USER_ROUTES).hasAnyRole("USER", "MODERATOR", "ADMIN")
//                 .pathMatchers(Routes.MODERATOR_ROUTES).hasAnyRole("MODERATOR", "ADMIN")
//                 .pathMatchers(Routes.ADMIN_ROUTES).hasRole("ADMIN")
//                 .and().build();
//     }
	
// }
