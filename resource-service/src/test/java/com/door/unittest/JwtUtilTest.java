package com.door.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import commons.dto.UserDTO;
import commons.jwt.JwtUtils;
import io.jsonwebtoken.Jwts;

@ExtendWith(MockitoExtension.class)
public class JwtUtilTest {
    
	/*
	 * VARS
	 * * */
    JwtUtils jwtUtils;
    Gson gson;

	String email = "jorge@gmail.com";
	String name = "Holge";
	List<String> rol = Arrays.asList("ROLE_ADMIN");
	String jwtSecret = "jwtKeyWithAlmost512bitesToABestProtectionMyNameIsHolgeAndIAmDoingTestsingThisKeyIsTooLongIAmBoring";
	
	/*
	 * MOCKS
	 * * */
	@Mock
	UserDTO userDTO;
    
    /*
	 * PREPARE
	 * * */
	@BeforeEach
	public void tearUp() {
		when(userDTO.getEmail()).thenReturn(email);
		when(userDTO.getName()).thenReturn(name);
		when(userDTO.getRol()).thenReturn(rol);
		
        gson = new Gson();
        jwtUtils = new JwtUtils();

		ReflectionTestUtils.setField(jwtUtils, "jwtSecret", jwtSecret);
		ReflectionTestUtils.setField(jwtUtils, "jwtExpirationMs", 30000);
        
    }

    @AfterEach
    public void tearDown(){
        
    }

    /*
	 * TEST
	 * * */


}