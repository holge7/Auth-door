package com.door.unittest.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.door.loginservice.dto.UserDTO;
import com.door.loginservice.security.JwtUtils;
import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@ExtendWith(MockitoExtension.class)
public class JwtUtilsTest {
    
    /*
     * VARS
     */
    JwtUtils jwtUtils;
    Gson gson;
    
    String jwtSecret = "jwtKeyWithAlmost512bitesToABestProtectionMyNameIsHolgeAndIAmDoingTestsingThisKeyIsTooLongIAmBoring";
    String email = "jorge@gmail.com";
    String name = "Holge";
    List<String> rolList = Arrays.asList("ROLE_ADMIN"); 
    String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJlbWFpbFwiOlwiam9yZ2VAZ21haWwuY29tXCIsXCJuYW1lXCI6XCJIb2xnZVwiLFwicm9sZXNcIjpbXCJST0xFX0FETUlOXCJdfSIsImlhdCI6MTY3MTk5NzQwMywiZXhwIjoxNjcyOTk3NDAzfQ.EoTpIccCsiORWtFp9R4UUO4y8xJR81K9pfm9gFjIOU-Ng7CqzImSFCFte4hIFqVZoRYCANSbOjNg6FsEMaUlqw";
    String jwtBad = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJlbWFpbFwiOlwiam9yZ2VAZ21haWwuY29tXCIsXCJuYW1lXCI6XCJIb2xnZVwiLFwicm9sZXNcIjpbXCJST0xFX0FETUlOXCJdfSIsImlhdCI6MTY3MTk5NzQwMywiZXhwIjoxNjcyOTk3dddddd.EoTpIccCsiORWtFp9R4UUO4y8xJR81K9pfm9gFjIOU-Ng7CqzImSFCFte4hIFqVZoRYCANSbOjNg6FsEMadddd";
    
    
    /*
     * MOCKS
     */
    static UserDTO userDTO = mock(UserDTO.class); 

    /*
     * PREPARE
     */
    @BeforeEach
    public void tearUp() {
        // UserDTO
        when(userDTO.getEmail()).thenReturn(email);
        when(userDTO.getName()).thenReturn(name);
        when(userDTO.getRol()).thenReturn(rolList);
    
        gson = new Gson();
        jwtUtils = new JwtUtils(gson);

        // Reflection
        ReflectionTestUtils.setField(jwtUtils, "jwtSecret", jwtSecret);
        ReflectionTestUtils.setField(jwtUtils, "jwtExpirationMs", 30000);
            
    }
    
    @AfterEach
    public void tearDown() {

    }

    /*
     * TEST
     */
    @Test
    public void generate_jwt() {
        String jwt = jwtUtils.generateJwtToken(userDTO);

        String regex = "^[\\w-]+\\.[\\w-]+\\.[\\w-]+$";
        Pattern pattern = Pattern.compile(regex);
        System.out.println(jwt);
        assertNotNull(jwt);
        assertTrue(pattern.matcher(jwt).matches());
    }

    @Test
    public void get_all_claims_jwt() {
        Jws<Claims> data = jwtUtils.getAllClaimsFromToken(jwt);

        assertEquals("HS512", data.getHeader().get("alg"));

        UserDTO userDTOjwt = gson.fromJson((String) data.getBody().get("sub"), UserDTO.class); 

        assertEquals(email, userDTOjwt.getEmail());
        assertEquals(name, userDTOjwt.getName());
        // No assert role because gson dont parse it :/
    }

    @Test
    public void validate_jwt() {
        Boolean isValid = jwtUtils.validateJwtToken(jwt);
        assertTrue(isValid);
    }

    @Test
    public void validate_jwt_bad() {
        Boolean isValid = jwtUtils.validateJwtToken(jwtBad);
        assertFalse(isValid);
    }

}
