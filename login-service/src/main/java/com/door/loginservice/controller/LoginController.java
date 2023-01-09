package com.door.loginservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.door.loginservice.dto.JwtResponse;
import com.door.loginservice.dto.LoginRequest;
import com.door.loginservice.utils.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

public interface LoginController {
	
	@Operation(
			summary = "Login with `email` and `password`", 
			description = "Returns userDTO and JWT for the user auth", 
			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse(
							responseCode = "200", 
							description = "Returns `userDTO` and `JWT` for the user auth",
							content = @Content(schema = @Schema(implementation = JwtResponse.class))),
					@io.swagger.v3.oas.annotations.responses.ApiResponse(
							responseCode = "400", 
							description = "Invalid request", 
							content = @Content(schema = @Schema(implementation = ApiResponse.class))),
					@io.swagger.v3.oas.annotations.responses.ApiResponse(
							responseCode = "500", 
							description = "Internal server error", 
							content = @Content(schema = @Schema(implementation = ApiResponse.class)))
					}
			)
	public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest user);
	
	@Operation(
			summary = "Valid if a JWT is valid or not", 
			description = "Returns true or false depends if the JWT is valid or not", 
			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse(
							responseCode = "200", 
							description = "Return if a JWT is valid or not",
							content = @Content(schema = @Schema(implementation = Boolean.class))),
					@io.swagger.v3.oas.annotations.responses.ApiResponse(
							responseCode = "400", 
							description = "Invalid request", 
							content = @Content(schema = @Schema(implementation = ApiResponse.class))),
					@io.swagger.v3.oas.annotations.responses.ApiResponse(
							responseCode = "500", 
							description = "Internal server error", 
							content = @Content(schema = @Schema(implementation = ApiResponse.class)))
					}
			)
	public ResponseEntity<ApiResponse> validJwt(@RequestParam String jwt);
	
	@Operation(
			summary = "Get the userDTO of a JWT", 
			description = "Returns userDTO assing a JWT", 
			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse(
							responseCode = "200", 
							description = "Return UserDTO of a JWT",
							content = @Content(schema = @Schema(implementation = JwtResponse.class))),
					@io.swagger.v3.oas.annotations.responses.ApiResponse(
							responseCode = "400", 
							description = "Invalid request", 
							content = @Content(schema = @Schema(implementation = ApiResponse.class))),
					@io.swagger.v3.oas.annotations.responses.ApiResponse(
							responseCode = "500", 
							description = "Internal server error", 
							content = @Content(schema = @Schema(implementation = ApiResponse.class)))
					}
			)
	public ResponseEntity<ApiResponse> getJwtDetail(@RequestParam String jwt) throws JsonMappingException, JsonProcessingException;
}
