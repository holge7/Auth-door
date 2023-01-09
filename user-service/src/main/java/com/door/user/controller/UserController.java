package com.door.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.door.user.data.dto.UserRegisterDTO;
import com.door.user.data.payload.request.LoginRequest;
import com.door.user.data.payload.request.SingupRequest;
import com.door.user.data.payload.response.JwtResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


public interface UserController {
	
	@Operation(
			summary = "Login with `email` and `password`", 
			description = "Returns userDTO", 
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "Returns `userDTO`",
							content = @Content(schema = @Schema(implementation = JwtResponse.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "Invalid request", 
							content = @Content(schema = @Schema(implementation = commons.utils.ApiResponse.class))),
					@ApiResponse(
							responseCode = "500", 
							description = "Internal server error", 
							content = @Content(schema = @Schema(implementation = commons.utils.ApiResponse.class)))
					}
			)
	public ResponseEntity<commons.utils.ApiResponse> login(@RequestBody LoginRequest user);
	

	@Operation(
			summary = "Register a new user", 
			description = "If user is register without `ROL` he will be a USER", 
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "Returns `userDTO` and `JWT` for the user auth",
							content = @Content(schema = @Schema(implementation = UserRegisterDTO.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "Invalid request", 
							content = @Content(schema = @Schema(implementation = commons.utils.ApiResponse.class))),
					@ApiResponse(
							responseCode = "409", 
							description = "Conflict", 
							content = @Content(schema = @Schema(implementation = commons.utils.ApiResponse.class))),
					@ApiResponse(
							responseCode = "500", 
							description = "Internal server error", 
							content = @Content(schema = @Schema(implementation = commons.utils.ApiResponse.class)))
					}
			)
	public ResponseEntity<commons.utils.ApiResponse> register(@RequestBody SingupRequest singUpRequest) throws Exception;
	

}
