package com.door.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.door.user.data.payload.request.LoginRequest;
import com.door.user.data.payload.request.SingupRequest;
import com.door.user.entity.User;
import com.door.user.service.UserService;
import com.door.user.utils.ApiResponse;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/test")
	public ResponseEntity<ApiResponse> test() {
		System.out.println("Estoy en el user controller :))))");
		System.out.println("En /test");
		System.out.println("··································");
		ApiResponse respnose = new ApiResponse("Hello");
		return new ResponseEntity<ApiResponse>(
					respnose,
					HttpStatus.OK
				);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(
			@RequestBody LoginRequest user
			){
		return userService.login(user);
	}
	
	/*@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(
			@RequestBody LoginRequest user
			){
		System.out.println("Estoy en el user controller :))))");
		return userService.login(user);
	}*/
	
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(
			@RequestBody SingupRequest singUpRequest
			) throws Exception {
		return userService.register(singUpRequest);
	}
	
}
