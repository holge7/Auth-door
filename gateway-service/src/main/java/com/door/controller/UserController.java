package com.door.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.door.dto.LoginRequest;
import com.door.service.UserService;
import com.door.utils.ApiResponse;

@RestController
public class UserController {
	
	UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(
			@RequestBody LoginRequest user
			) {
		return userService.loginService(user);
	}
	
}
