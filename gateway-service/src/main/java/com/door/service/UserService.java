package com.door.service;

import java.net.URI;
import java.util.HashMap;

import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerWebClientBuilderBeanPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.door.dto.JwtResponse;
import com.door.dto.LoginRequest;
import com.door.dto.UserDTO;
import com.door.jwt.JwtUtils;
import com.door.utils.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;

import reactor.core.publisher.Mono;

@Service
public class UserService {
    
    public RestTemplate restTemplate;
    public JwtUtils jwtUtils;
	
	public UserService(RestTemplate restTemplate, JwtUtils jwtUtils) {
		this.restTemplate = restTemplate;
		this.jwtUtils = jwtUtils;
	}
	
	public ResponseEntity<ApiResponse> loginService(LoginRequest user) {
		
		HashMap<String, String> params = new HashMap<>();
		params.put("email", user.getEmail());
		params.put("password", user.getPassword());
		
		System.out.println("Email");
		System.out.println(user.getEmail());

		ResponseEntity<String> response = new RestTemplate()
				.postForEntity("http://localhost:8085/api/user/login", params, String.class);
		
		Gson gson = new Gson();
		ApiResponse apiResponse = gson.fromJson(response.getBody(), ApiResponse.class);
		
		String userString = gson.toJson(apiResponse.getData());
		UserDTO userDTO = gson.fromJson(userString, UserDTO.class);
		
		JwtResponse jwtResponse = new JwtResponse(
				jwtUtils.generateJwtToken(userDTO), 
				userDTO.getName(), 
				userDTO.getEmail(), 
				userDTO.getRol());
		
		return new ResponseEntity<ApiResponse>(
					new ApiResponse(jwtResponse),
					HttpStatus.OK
				);
	}
	
}
