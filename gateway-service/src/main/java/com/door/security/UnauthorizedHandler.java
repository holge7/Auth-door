// package com.door.security;

// import java.nio.charset.StandardCharsets;

// import org.apache.commons.logging.Log;
// import org.apache.commons.logging.LogFactory;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.io.buffer.DataBuffer;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.server.reactive.ServerHttpResponse;
// import org.springframework.security.access.AccessDeniedException;
// import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
// import org.springframework.web.server.ServerWebExchange;

// import com.door.utils.ApiResponse;
// import com.google.gson.Gson;

// import reactor.core.publisher.Mono;

// @Configuration
// public class UnauthorizedHandler implements ServerAccessDeniedHandler {
// 	Log logger = LogFactory.getLog(getClass());

// 	Gson gson;
	
// 	public UnauthorizedHandler(Gson gson) {
// 		this.gson = gson;
// 	}

// 	@Override
// 	public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
// 		// Create response
//         ServerHttpResponse response = exchange.getResponse();
//         response.setStatusCode(HttpStatus.FORBIDDEN);
//         response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        
//         // Create body resposne
//         ApiResponse apiResponse = new ApiResponse(denied.getLocalizedMessage());
//         String responseBody = gson.toJson(apiResponse);

//         // Write response body
//         byte[] bytes = responseBody.getBytes(StandardCharsets.UTF_8);
//         DataBuffer buffer = response.bufferFactory().wrap(bytes);
        
//         // Return response body
//         return response.writeWith(Mono.just(buffer));
// 	}

// }
