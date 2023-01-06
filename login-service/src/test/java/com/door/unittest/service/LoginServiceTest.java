package com.door.unittest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.door.loginservice.dto.JwtResponse;
import com.door.loginservice.dto.LoginRequest;
import com.door.loginservice.dto.UserDTO;
import com.door.loginservice.exception.CustomGenericalException;
import com.door.loginservice.security.JwtUtils;
import com.door.loginservice.service.LoginService;
import com.door.loginservice.utils.ApiResponse;
import com.google.gson.Gson;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
    
    /*
     * VARS
     */
    LoginService loginService;
    
    String jwtSecret = "jwtKeyWithAlmost512bitesToABestProtectionMyNameIsHolgeAndIAmDoingTestsingThisKeyIsTooLongIAmBoring";
    String email = "jorge@gmail.com";
    String name = "Holge";
    String password = "password";
    List<String> rolList = Arrays.asList("ROLE_ADMIN"); 
    String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJlbWFpbFwiOlwiam9yZ2VAZ21haWwuY29tXCIsXCJuYW1lXCI6XCJIb2xnZVwiLFwicm9sZXNcIjpbXCJST0xFX0FETUlOXCJdfSIsImlhdCI6MTY3MTk5NzQwMywiZXhwIjoxNjcyOTk3NDAzfQ.EoTpIccCsiORWtFp9R4UUO4y8xJR81K9pfm9gFjIOU-Ng7CqzImSFCFte4hIFqVZoRYCANSbOjNg6FsEMaUlqw";
    String jwtBad = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJlbWFpbFwiOlwiam9yZ2VAZ21haWwuY29tXCIsXCJuYW1lXCI6XCJIb2xnZVwiLFwicm9sZXNcIjpbXCJST0xFX0FETUlOXCJdfSIsImlhdCI6MTY3MTk5NzQwMywiZXhwIjoxNjcyOTk3dddddd.EoTpIccCsiORWtFp9R4UUO4y8xJR81K9pfm9gFjIOU-Ng7CqzImSFCFte4hIFqVZoRYCANSbOjNg6FsEMadddd";
    
    Gson gson;

    /*
    * MOCKS
    */
    RestTemplate restTemplate = mock(RestTemplate.class);
    JwtUtils jwtUtils = mock(JwtUtils.class);
    LoginRequest loginRequest = mock(LoginRequest.class);
    ApiResponse apiResponse = mock(ApiResponse.class);
    UserDTO userDTO = mock(UserDTO.class);
    
    /*
     * PREPARE
     */
    ResponseEntity<String> badResponse;
    @BeforeEach
    public void tearUp() {
        gson = new Gson();


        // JWT UTILS
        when(jwtUtils.validateJwtToken(jwt)).thenReturn(true);
        when(jwtUtils.validateJwtToken(jwtBad)).thenReturn(false);

        when(jwtUtils.generateJwtToken(any())).thenReturn(jwt);

        // LOGIN REQUEST
        when(loginRequest.getEmail()).thenReturn(email);
        when(loginRequest.getPassword()).thenReturn(password);

        // USER DTO
        when(userDTO.getEmail()).thenReturn(email);
        when(userDTO.getName()).thenReturn(name);
        when(userDTO.getRol()).thenReturn(rolList);

        // API RESPONSE
        when(apiResponse.getError()).thenReturn(false);
        when(apiResponse.getMessage()).thenReturn("");
        when(apiResponse.getData()).thenReturn(userDTO);


        //REST TEMPLATE
        UserDTO userDTOReal = new UserDTO(email, name, rolList);
        ApiResponse good = new ApiResponse(userDTOReal);
        ApiResponse bad = new ApiResponse(true);
        String goodApiResponseString = gson.toJson(good);
        String badApiResponseString = gson.toJson(bad);

       ResponseEntity<String> goodResponse = new ResponseEntity(
        goodApiResponseString,
            HttpStatus.OK
        );

        badResponse = new ResponseEntity(
            badApiResponseString,
            HttpStatus.OK
        );

        when(restTemplate.postForEntity(eq("http://localhost:8080/api/user/login"), any(), eq(String.class)))
        .thenReturn(goodResponse);

        loginService = new LoginService(restTemplate, gson, jwtUtils);

    }

     /*
      * TEST
      */
    @Test
    public void valid_jwt() {
        ApiResponse response = loginService.validJwt(jwt);
        assertFalse(response.getError());
    }
    @Test
    public void valid_jwt_bad() {
        ApiResponse response = loginService.validJwt(jwtBad);
        assertTrue(response.getError());
    }
    
    //@Test
    public void get_jwt_detail() {
        String claimsJwtStr = "header={alg=HS512},body={sub={'email':'jorge@gmail.com','name':'Holge','rol':['ROLE_ADMIN']}, iat=1671997403, exp=1672997403},signature=EoTpIccCsiORWtFp9R4UUO4y8xJR81K9pfm9gFjIOU-Ng7CqzImSFCFte4hIFqVZoRYCANSbOjNg6FsEMaUlqw";


        ApiResponse response = loginService.getJwtDetail(jwt);

        // header={alg=HS512},body={sub={"email":"jorge@gmail.com","name":"Holge","roles":["ROLE_ADMIN"]}, iat=1671997403, exp=1672997403},signature=EoTpIccCsiORWtFp9R4UUO4y8xJR81K9pfm9gFjIOU-Ng7CqzImSFCFte4hIFqVZoRYCANSbOjNg6FsEMaUlqw
        System.out.println(response);
    }

    @Test
    public void login() {
        ApiResponse response = loginService.login(loginRequest);
    
        assertNotNull(response);
        assertFalse(response.getError());

        JwtResponse jwtResponse = (JwtResponse) response.getData();
        
        assertEquals(jwt, jwtResponse.getToken());
        assertEquals(name, jwtResponse.getUsername());
        assertEquals(email, jwtResponse.getEmail());
    }

    @Test
    public void login_apiResponse_with_error() {
        when(restTemplate.postForEntity(eq("http://localhost:8080/api/user/login"), any(), eq(String.class)))
        .thenReturn(badResponse);

        try {
            loginService.login(loginRequest);
        } catch (CustomGenericalException e) {
            assertNotNull(e);
        } 

    }

    
}
