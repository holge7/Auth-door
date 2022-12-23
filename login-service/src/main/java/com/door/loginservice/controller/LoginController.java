package com.door.loginservice.controller;

import org.springframework.http.ResponseEntity;
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

    @PostMapping("")
	public ResponseEntity<ApiResponse> login(
			@RequestBody LoginRequest user
			) {
		System.out.println("Hello world");
		return loginService.login(user);
	}

	@PostMapping("/valid")
	public ResponseEntity<ApiResponse> validJwt(
		@RequestParam String jwt
	) {
		return loginService.validJwt(jwt);
	}

	@PostMapping("/detail")
	public ResponseEntity<ApiResponse> getJwtDetail(
		@RequestBody String jwt
	) throws JsonMappingException, JsonProcessingException {
		return loginService.getJwtDetail(jwt);
	}


}
