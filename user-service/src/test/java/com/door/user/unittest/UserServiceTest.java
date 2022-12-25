package com.door.user.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.door.user.data.ERol;
import com.door.user.data.dto.UserDTO;
import com.door.user.data.payload.request.LoginRequest;
import com.door.user.data.payload.request.SingupRequest;
import com.door.user.entity.Rol;
import com.door.user.entity.User;
import com.door.user.exception.RolException;
import com.door.user.exception.UserException;
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
    static String emailAlreadyRegister = "already@register.com";

    static List<String> rolesList = Arrays.asList("ROLE_USER");
    
    static Rol rol = new Rol(ERol.ROLE_USER);
    static Set<Rol> rolesSet = new HashSet<>(Arrays.asList(rol));

    /*
	 * MOCKS
	 * * */

    static User user = mock(User.class);
    static UserDTO userDTO = mock(UserDTO.class);
    static SingupRequest singupRequest = mock(SingupRequest.class);
    static LoginRequest loginRequest = mock(LoginRequest.class);
    static UserRepository userRepository = mock(UserRepository.class);
    static UserMapper userMapper = mock(UserMapper.class);
    static RolRepository rolRepository = mock(RolRepository.class);
    static PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);


    /*
	 * PREPARE
	 * * */
    @BeforeEach
    public void tearUp() {
        // USER
        when(user.getId()).thenReturn(id);
        when(user.getEmail()).thenReturn(email);
        when(user.getName()).thenReturn(name);
        when(user.getPassword()).thenReturn(password);

        // USER DTO
        when(userDTO.getEmail()).thenReturn(email);
        when(userDTO.getName()).thenReturn(email);
        when(userDTO.getRol()).thenReturn(rolesList);

        //  SING UP REQUEST
        when(singupRequest.getEmail()).thenReturn(email);
        when(singupRequest.getUsername()).thenReturn(name);
        when(singupRequest.getRole()).thenReturn(rolesSet);
        when(singupRequest.getPassword()).thenReturn(password);

        // USER REPOSITORY
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(userRepository.existsByEmail(email)).thenReturn(false);
        when(userRepository.existsByEmail(emailAlreadyRegister)).thenReturn(true);

        // ROL REPOSITORY
        when(rolRepository.findByRol(ERol.ROLE_USER)).thenReturn(Optional.of(new Rol(ERol.ROLE_USER)));

        // USER MAPPER
        UserDTO userDTO = new UserDTO(email, name, rolesSet);
        when(userMapper.userDTO(any())).thenReturn(userDTO);

        userService = new UserService(userRepository, rolRepository, passwordEncoder, userMapper);
    }

    @AfterEach
    public void tearDown() {

    }

    /*
	 * TEST
	 * * */

    // Login
    @Test
    public void login() {
        // Mock
        when(loginRequest.getEmail()).thenReturn(email);
        when(loginRequest.getPassword()).thenReturn(password);

        // Call
        ApiResponse response = userService.login(loginRequest);
        assertFalse(response.getError());

        UserDTO userDTO = (UserDTO) response.getData();
        assertNotNull(userDTO);
        assertEquals(email, userDTO.getEmail());
        assertEquals(name, userDTO.getName());
    }
    @Test
    public void login_bad() {
        //Mock
        when(loginRequest.getEmail()).thenReturn("null");
        when(loginRequest.getPassword()).thenReturn("null");

        try {
            userService.login(loginRequest);            
        } catch (UserException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getHttpStatus());
        }
    }

    // Register
    @Test
    public void register() {
        ApiResponse response = userService.register(singupRequest);
        
        assertNotNull(response);
        assertFalse(response.getError());

        UserDTO userDTO = (UserDTO) response.getData();
        assertEquals(email, userDTO.getEmail());
        assertEquals(name, userDTO.getName());
        assertEquals(rolesList, userDTO.getRol());
    }
    @Test
    public void register_with_user_already_register() {
        when(singupRequest.getEmail()).thenReturn(emailAlreadyRegister);

        try {
            userService.register(singupRequest);
        } catch (UserException e) {
            assertNotNull(e);
            assertEquals(HttpStatus.CONFLICT, e.getHttpStatus());
        } catch (Exception e) {
            // Other exception not should be throwed
            assertTrue(false);
        }

    }
    @Test
    public void register_user_with_bad_role() {
        when(rolRepository.findByRol(ERol.ROLE_USER)).thenReturn(Optional.empty());
        
        try {
            userService.register(singupRequest);
        } catch (RolException e) {
            assertNotNull(e);
            assertEquals(HttpStatus.NOT_FOUND, e.getHttpStatus());
        } catch (Exception e) {
            // Other exception not should be throwed
            assertTrue(false);
        }
    }

}
