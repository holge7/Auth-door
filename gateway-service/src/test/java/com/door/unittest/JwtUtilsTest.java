package com.door.unittest;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import com.door.dto.UserDTO;
import com.door.jwt.JwtUtils;

@ExtendWith(MockitoExtension.class)
@TestPropertySource("/application.properties")
@ActiveProfiles("test")
public class JwtUtilsTest {
	
	/*
	 * VARS
	 * * */
	String email = "jorge@gmail.com";
	String name = "Holge";
	List<String> roles = Arrays.asList("ROLE_ADMIN");
	
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
		
		ReflectionTestUtils.setField(jwtUtils, "jwtSecret", "jwtKeyWithAlmost512bitesToABestProtectionMyNameIsHolgeAndIAmDoingTestsingThisKeyIsTooLongIAmBoring");
		ReflectionTestUtils.setField(jwtUtils, "jwtExpirationMs", 30000);
		
		System.out.println("Before each");
	}
	
	@AfterEach
	public void tearDown() {
		System.out.println("After each");
	}
	

	/*
	 * TEST
	 * * */
	
	@Test
	public void generate_jwt_token() {
		String jwt = jwtUtils.generateJwtToken(userDTO);
		assertNotNull(jwt);
	}
	
	
	
	
	
	
	
	
}
