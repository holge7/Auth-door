package com.door.user.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.door.user.data.dto.UserDTO;
import com.door.user.data.dto.UserRegisterDTO;
import com.door.user.data.payload.request.LoginRequest;
import com.door.user.entity.User;
import com.door.user.mapper.UserMapper;
import com.door.user.repository.RolRepository;
import com.door.user.repository.UserRepository;
import com.door.user.service.UserService;
import com.door.user.utils.ApiResponse;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    /*
	 * VARS
	 * * */
    UserService userService;
    static Long id = 1L;
    static String email = "jorge@gmail.com";
    static String name = "Holge";
    static String password = "password";
    static List<String> roles = Arrays.asList("ROLE_USER");

    /*
	 * MOCKS
	 * * */
    // static User user = mock(User.class);
    static User user;

    static {
        user = new User(email, name, password);
        // when(user.getId()).thenReturn(id);
        // when(user.getEmail()).thenReturn(email);
        // when(user.getName()).thenReturn(name);
        // when(user.getPassword()).thenReturn(password);
    }

    static UserDTO userDTO = mock(UserDTO.class);
    static {
        when(userDTO.getEmail()).thenReturn(email);
        when(userDTO.getName()).thenReturn(email);
        when(userDTO.getRol()).thenReturn(roles);
    }

    static LoginRequest loginRequest = mock(LoginRequest.class);
    static {
        when(loginRequest.getEmail()).thenReturn(email);
        when(loginRequest.getPassword()).thenReturn("password");
    }


    static UserRepository userRepository = mock(UserRepository.class);
    static {
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));
    }

    RolRepository rolRepository = mock(RolRepository.class);
    PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    
    static UserMapper userMapper = mock(UserMapper.class);
    static {
        when(userMapper.userDTO(any())).thenReturn(userDTO);
    }


    /*
	 * PREPARE
	 * * */
    @BeforeEach
    public void tearUp() {
        userService = new UserService(userRepository, rolRepository, passwordEncoder, userMapper);
    }

    @AfterEach
    public void tearDown() {

    }

    /*
	 * TEST
	 * * */

    @Test
    public void login() {
        ResponseEntity<ApiResponse> response = userService.login(loginRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ApiResponse apiResponse = response.getBody();
        System.out.println(apiResponse);
    
    
    }


}
