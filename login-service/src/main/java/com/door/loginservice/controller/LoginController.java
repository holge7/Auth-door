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
		System.out.println("Llego a detail");
		System.out.println(jwt);
		return loginService.getJwtDetail(jwt);
	}

	@PostMapping("/detail2")
	public ResponseEntity<ApiResponse> getJwtDetail2() throws JsonMappingException, JsonProcessingException {
		String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJlbWFpbFwiOlwiam9yZ2UzQGdtYWlsLmNvbVwiLFwicm9sZXNcIjpbXCJST0xFX1VTRVJcIl19IiwiaWF0IjoxNjcxNjUyNjE0LCJleHAiOjE2NzE3MzkwMTR9.a8NglB39EPNi1Y1Ofb1a2WXluxnPAhIj2XbYDsKIrr0_CWX3mAjC6Oh6IVav3FQ1COyZUsbcO4Go5xGFLRPRkg";
		return loginService.getJwtDetail(jwt);
	}

}
