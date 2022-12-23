package com.door.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.door.dto.UserDTO;
import com.door.security.JwtUtil;
import com.google.gson.Gson;

@ExtendWith(MockitoExtension.class)
public class JwtUtilTest {
    
    /*
	 * VARS
	 * * */
    JwtUtil jwtUtil;
    String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJlbWFpbFwiOlwiam9yZ2VAZ21haWwuY29tXCIsXCJuYW1lXCI6XCJqb3JnZVwiLFwicm9sZXNcIjpbXCJST0xFX0FETUlOXCJdfSIsImlhdCI6MTY3MTgxNjk0MywiZXhwIjoxNjcxOTAzMzQzfQ.hSl82BWLfsk8nzKrmIYGcQ_fF3dE3fCHxdD_CBW2TFRbe3IVegC2B5Z9fq-yhqeB5zhZFw-YVadAy-kMQsCQeA";
    
    /*
    * MOCKS
    * * */

    /*
	 * PREPARE
	 * * */
    @BeforeEach
    public void tearUp(){
        Gson gson = new Gson();
        jwtUtil = new JwtUtil(gson);
    }

    @AfterEach
    public void tearDown(){
        
    }

    /*
	 * TEST
	 * * */
    @Test
    public void get_user_from_jwt() {
        UserDTO user = jwtUtil.getUserFromJwt(jwt);

        List<String> roles = Arrays.asList("ROLE_ADMIN");

        assertEquals("jorge@gmail.com", user.getEmail());
        assertEquals("jorge", user.getName());
        assertEquals(roles, user.getRol());
    }



}