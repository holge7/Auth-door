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
    String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJlbWFpbFwiOlwiam9yZ2VAZ21haWwuY29tXCIsXCJuYW1lXCI6XCJqb3JnZVwiLFwicm9sZXNcIjpbXCJST0xFX0FETUlOXCJdfSIsImlhdCI6MTY3MTk5MjgyNSwiZXhwIjoxNjcyOTkyODI1fQ.DS4V83QwF__p1j7nVJAyb-pl0Ay8cl15ApRqYhwIzTGPDVWji5pLDZlGoAKLsFAbeo1jNmYAPYz6S8Xt2us2Xg";
    
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