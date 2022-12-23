package com.door.loginservice.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.door.loginservice.dto.JwtResponse;
import com.door.loginservice.dto.LoginRequest;
import com.door.loginservice.dto.UserDTO;
import com.door.loginservice.security.JwtData;
import com.door.loginservice.security.JwtUtils;
import com.door.loginservice.utils.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


@Service
public class LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);


    RestTemplate restTemplate;
    Gson gson;
    JwtUtils jwtUtils;

    public LoginService(RestTemplate restTemplate, Gson gson, JwtUtils jwtUtils) {
        this.restTemplate = restTemplate;
        this.gson = gson;
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<ApiResponse> validJwt(String jwt) {
        // reverse result
        boolean isValid = !jwtUtils.validateJwtToken(jwt);

        return new ResponseEntity<ApiResponse>(
            new ApiResponse("Invalid jwt", isValid, null),
            HttpStatus.OK
        );
    }

    public ResponseEntity<ApiResponse> getJwtDetail(String jwt) throws JsonMappingException, JsonProcessingException {
        if (!jwtUtils.validateJwtToken(jwt)) {
            return new ResponseEntity<ApiResponse>(
                new ApiResponse("Invalid jwt"),
                HttpStatus.UNAUTHORIZED
            );
        }

        // Decrypt and get all info about the JWT
        var claims = jwtUtils.getAllClaimsFromToken(jwt);

        ObjectMapper objectMapper = new ObjectMapper();
        JwtData user = objectMapper.readValue(claims.getBody().getSubject(), JwtData.class);

        return new ResponseEntity<ApiResponse>(
                new ApiResponse("Hello World!", false, user),
                HttpStatus.OK
            );
    }
    
    public ResponseEntity<ApiResponse> login(LoginRequest user) {

        HashMap<String, String> params = new HashMap<>();
		params.put("email", user.getEmail());
		params.put("password", user.getPassword());
        
        ResponseEntity<String> response = null;
        ApiResponse apiResponse = null;

        try {
            response =  restTemplate.postForEntity("http://localhost:8085/api/user/login", params, String.class);
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

        return new ResponseEntity<>(
            new ApiResponse(jwtResponse),
            HttpStatus.OK
        );
    }

}