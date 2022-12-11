package com.door.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.door.dto.JwtResponse;
import com.door.dto.LoginRequest;
import com.door.dto.UserDTO;
import com.door.jwt.JwtUtils;
import com.door.utils.ApiResponse;
import com.google.gson.Gson;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
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

		
		ResponseEntity<String> response = null;
		Gson gson = new Gson();
		ApiResponse apiResponse;
		try {
			response = new RestTemplate()
					.postForEntity("http://localhost:8085/api/user/login", params, String.class);
			
			apiResponse = gson.fromJson(response.getBody(), ApiResponse.class);
			
		} catch (HttpClientErrorException e) {
			apiResponse = gson.fromJson(e.getResponseBodyAsString(), ApiResponse.class);
		}
	
		if (apiResponse.getError()) {
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(apiResponse.getMessage()),
					HttpStatus.NOT_FOUND
				);
		}

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
