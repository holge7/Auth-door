package com.door.loginservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.door.loginservice.dto.LoginRequest;
import com.door.loginservice.service.LoginService;
import com.door.loginservice.utils.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@GetMapping("/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<>(
			"Hello world",
			HttpStatus.OK
		);
	}

    @PostMapping("")
	public ResponseEntity<ApiResponse> login(
			@RequestBody LoginRequest user
			) {
		return new ResponseEntity<>(
			loginService.login(user),
			HttpStatus.OK
		);
	}

	@PostMapping("/valid")
	public ResponseEntity<ApiResponse> validJwt(
		@RequestParam String jwt
	) {
		return new ResponseEntity<>(
			loginService.validJwt(jwt),
			HttpStatus.OK
		);
	}

	@PostMapping("/detail")
	public ResponseEntity<ApiResponse> getJwtDetail(
		@RequestParam String jwt
	) throws JsonMappingException, JsonProcessingException {
		return new ResponseEntity<>(
			loginService.getJwtDetail(jwt),
			HttpStatus.OK
		);
	}


}
