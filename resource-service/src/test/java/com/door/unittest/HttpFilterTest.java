package com.door.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.door.dto.UserDTO;
import com.door.security.HttpFilter;
import com.door.security.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

//Enable mockito support
@ExtendWith(MockitoExtension.class)
public class HttpFilterTest {
    
	/*
	 * VARS
	 * * */
	HttpFilter httpFilter;

     /*
	 * MOCKS
	 * * */
	// USER DTO
	static UserDTO user = mock(UserDTO.class);
	static {
		when(user.getEmail()).thenReturn("jorge@gmail.com");
		when(user.getName()).thenReturn("Holge");
	}


	// REQUEST
	static HttpServletRequest httpRequestAuth = mock(HttpServletRequest.class);
	static HttpServletRequest httpRequestNoAuth = mock(HttpServletRequest.class);
	static{
		when(httpRequestAuth.getHeader("authorization")).thenReturn("dummy jwt string");
	}

	// JWT UTILS
	static JwtUtil jwtUtil = mock(JwtUtil.class);
	static{
		when(jwtUtil.getUserFromJwt(anyString())).thenReturn(user);
	}


     /*
	 * PREPARE
	 * * */
	@BeforeEach
	public void tearUp() {
        httpFilter = new HttpFilter(jwtUtil);
    }

    @AfterEach
	public void tearDown() {
		
    }


     /*
	 * TEST
	 * * */
	@Test
	public void get_user_auth() {
		httpFilter.getUser(httpRequestAuth);
		assertEquals(user, HttpFilter.user);
		assertTrue(HttpFilter.isAuth);
	}

	@Test
	public void get_user_no_auth() {
		httpFilter.getUser(httpRequestNoAuth);
		System.out.println(HttpFilter.user);

		assertFalse(HttpFilter.isAuth);
	}

}
