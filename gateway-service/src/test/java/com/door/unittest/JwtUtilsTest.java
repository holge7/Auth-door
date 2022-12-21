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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.door.dto.UserDTO;
import com.door.jwt.JwtData;
import com.door.jwt.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;

@ExtendWith(MockitoExtension.class)
public class JwtUtilsTest {
	
	/*
	 * VARS
	 * * */
	String email = "jorge@gmail.com";
	String name = "Holge";
	List<String> roles = Arrays.asList("ROLE_ADMIN");
	String jwtSecret = "jwtKeyWithAlmost512bitesToABestProtectionMyNameIsHolgeAndIAmDoingTestsingThisKeyIsTooLongIAmBoring";
	
	/*
	 * MOCKS
	 * * */
	@InjectMocks
	JwtUtils jwtUtils;
	
	@Mock
	UserDTO userDTO;

	/*
	 * PREPARE
	 * * */
	@BeforeEach
	public void tearUp() {
		when(userDTO.getEmail()).thenReturn(email);
		when(userDTO.getName()).thenReturn(name);
		when(userDTO.getRol()).thenReturn(roles);
		
		ReflectionTestUtils.setField(jwtUtils, "jwtSecret", jwtSecret);
		ReflectionTestUtils.setField(jwtUtils, "jwtExpirationMs", 30000);
	}
	
	@AfterEach
	public void tearDown() {
	}
	

	/*
	 * TEST
	 * * */
	
	@Test
	public void generate_jwt_token() throws JsonMappingException, JsonProcessingException {
		//Generate jwt
		String jwt = jwtUtils.generateJwtToken(userDTO);
		assertNotNull(jwt);

		//Assert jwt data
		var claims = Jwts.parser()
			.setSigningKey(jwtSecret)
			.parseClaimsJws(jwt);

		// Parse jwt to JwtData
		ObjectMapper objectMapper = new ObjectMapper();
		JwtData user = objectMapper.readValue(claims.getBody().getSubject(), JwtData.class);
		
		//Assert jwt data 
		assertEquals(email, user.getEmail());
		assertEquals(name, user.getName());
		assertEquals(roles,user.getRoles());
	}

	@Test
	public void get_all_claims_from_jwt() throws JsonMappingException, JsonProcessingException {
		//Generate JWT
		String jwt = jwtUtils.generateJwtToken(userDTO);

		var claims = jwtUtils.getAllClaimsFromToken(jwt);
		assertNotNull(claims);

		// Parse jwt to JwtData
		ObjectMapper objectMapper = new ObjectMapper();
		JwtData user = objectMapper.readValue(claims.getBody().getSubject(), JwtData.class);
		
		//Assert jwt data 
		assertEquals(email, user.getEmail());
		assertEquals(name, user.getName());
		assertEquals(roles,user.getRoles());
	}
	
	@Test
	public void validate_JWT_good() {
		//Generate JWT
		String jwt = jwtUtils.generateJwtToken(userDTO);
	
		assertTrue(jwtUtils.validateJwtToken(jwt));
	}

}
