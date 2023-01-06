// package com.door.security;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.http.ResponseEntity;
// import org.springframework.http.server.reactive.ServerHttpRequest;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContext;
// import org.springframework.security.core.context.SecurityContextImpl;
// import org.springframework.security.web.server.context.ServerSecurityContextRepository;
// import org.springframework.stereotype.Component;
// import org.springframework.web.client.RestTemplate;
// import org.springframework.web.server.ServerWebExchange;

// import com.door.jwt.JwtData;
// import com.door.jwt.JwtUtils;
// import com.door.utils.ApiResponse;
// import com.google.common.net.HttpHeaders;
// import com.google.gson.Gson;

// import reactor.core.publisher.Mono;

// @Component
// public class SecurityContextRepository implements ServerSecurityContextRepository {
//     private static final Logger log = LoggerFactory.getLogger(SecurityContextRepository.class);
	
// 	public CustomAuthenticationManager customAuthenticationManager;
// 	public JwtUtils jwtUtils;
//     public RestTemplate restTemplate;
//     public Gson gson;

//     public SecurityContextRepository(CustomAuthenticationManager customAuthenticationManager, JwtUtils jwtUtils, RestTemplate restTemplate, Gson gson) {
//         this.customAuthenticationManager = customAuthenticationManager;
//         this.jwtUtils = jwtUtils;
//         this.restTemplate = restTemplate;
//         this.gson = gson;
//     }
	
// 	@Override
//     public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
//         throw new UnsupportedOperationException("Not supported yet.");
//     }

//     @Override
//     public Mono<SecurityContext> load(ServerWebExchange swe) {
//         //Load token from Authorization header and pass it along so we can use it for authentication
//         ServerHttpRequest request = swe.getRequest();
//         String jwt = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

//         ResponseEntity<ApiResponse> jwtValidate = jwtGetDetails(jwt);

//         JwtData jwtData = gson.fromJson(jwtValidate.getBody().getData().toString(), JwtData.class);

//         // validamos que jwt != null, es valido y obtenemos detealles
//         if (!jwtValidate.getBody().getError()) {
//             System.out.println("==============");
//             System.out.println("JWT VALIDADO");
//             System.out.println("==============");
//             JwtData test = new JwtData(jwtData.getEmail(), jwtData.getName(), jwtData.getRoles());

//         	// Create Authentication user data
//             Authentication auth = new UsernamePasswordAuthenticationToken(test, test);
            
//             // Use authManager to set authentication/authorization
//             return this.customAuthenticationManager.authenticate(auth)
//                     .map(SecurityContextImpl::new);
//         } else {
//             System.out.println("==============");
//             System.out.println("ERROR");
//             System.out.println("==============");
//             log.warn("JWT not valid");
//             return Mono.empty();
//         }
//     }

//     public ResponseEntity<ApiResponse> jwtGetDetails(String jwt) {
//         //Do request to Login service: /api/login/detail
//         String url = "http://login-service/api/login/detail";
//         System.out.println("====================");
//         System.out.println("I WILL CALL TO LOGIN service");
//         System.out.println("====================");
//         ResponseEntity<ApiResponse> apiResponse = restTemplate.postForEntity(url, jwt, ApiResponse.class);
//         return apiResponse;
//     }


// }