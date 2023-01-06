package com.door.loginservice.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.door.loginservice.dto.JwtResponse;
import com.door.loginservice.dto.LoginRequest;
import com.door.loginservice.dto.UserDTO;
import com.door.loginservice.exception.CustomGenericalException;
import com.door.loginservice.security.JwtUtils;
import com.door.loginservice.utils.ApiResponse;
import com.google.gson.Gson;

@Service
public class LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    Gson gson;
    RestTemplate restTemplate;
    JwtUtils jwtUtils;

    public LoginService(RestTemplate restTemplate, Gson gson, JwtUtils jwtUtils) {
        this.restTemplate = restTemplate;
        this.jwtUtils = jwtUtils;
        this.gson = gson;
    }

    public ApiResponse validJwt(String jwt) {
        // reverse result
        boolean isValid = jwtUtils.validateJwtToken(jwt);

        if (isValid) {
            return new ApiResponse("Valid jwt", false);
        }
        return new ApiResponse("Invalid jwt", true);
    }

    public ApiResponse getJwtDetail(String jwt) {
        // Decrypt and get all info about the JWT
        UserDTO user = jwtUtils.getUserFromJwt(jwt);

        return new ApiResponse(user);
    }
    
    public ApiResponse login(LoginRequest user) {

        HashMap<String, String> params = new HashMap<>();
		params.put("email", user.getEmail());
		params.put("password", user.getPassword());
        
        ResponseEntity<String> response = null;
        ApiResponse apiResponse = null;

        response = restTemplate.postForEntity("http://user-service/api/user/login", params, String.class);
        apiResponse = gson.fromJson(response.getBody(), ApiResponse.class);

        if (apiResponse.getError()) {
            logger.error(apiResponse.getMessage());
            throw new CustomGenericalException(
                apiResponse.getMessage(), 
                HttpStatus.NOT_FOUND);
		}
        
		String userString = gson.toJson(apiResponse.getData());
		UserDTO userDTO = gson.fromJson(userString, UserDTO.class);

        System.out.println("Api response:");
        System.out.println(apiResponse.getData());

            System.out.println("User String");
            System.out.println(userString);

        System.out.println("User dto");
            System.out.println(userDTO);

		JwtResponse jwtResponse = new JwtResponse(
				jwtUtils.generateJwtToken(userDTO), 
				userDTO.getName(), 
				userDTO.getEmail(), 
				userDTO.getRol());

        return new ApiResponse(jwtResponse);
    }

}