package com.door.user.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ctc.wstx.util.StringUtil;
import com.door.user.data.ERol;
import com.door.user.data.dto.UserDTO;
import com.door.user.data.payload.request.LoginRequest;
import com.door.user.data.payload.request.SingupRequest;
import com.door.user.entity.Rol;
import com.door.user.entity.User;
import com.door.user.exception.RolNotFoundException;
import com.door.user.exception.UserAlreadyExistsException;
import com.door.user.exception.UserException;
import com.door.user.exception.UserNotFoundException;
import com.door.user.mapper.UserMapper;
import com.door.user.repository.RolRepository;
import com.door.user.repository.UserRepository;
import com.door.user.utils.ApiResponse;


@Service
public class UserService {
	
	public UserRepository userRepository;
	public RolRepository rolRepository;
	public PasswordEncoder passwordEncoder;
	public UserMapper userMapper;
	public Mapper mapper;
	//public AuthenticationManager authenticationManager;
	//public JwtUtils jwtUtils;
	
	public UserService(UserRepository userRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder,
			UserMapper userMapper, Mapper mapper) {
		this.userRepository = userRepository;
		this.rolRepository = rolRepository;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
		this.mapper = mapper;
	}
	
	
	public ResponseEntity<ApiResponse> login(LoginRequest userLogin){
		
		String email = userLogin.getEmail();
		
		//Validate the existence of the user/password
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException(email));
		
		UserDTO userDTO = userMapper.userDTO(user);		
		ApiResponse response = new ApiResponse(userDTO);
		
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	
	
	public ResponseEntity<ApiResponse> register(SingupRequest userRequest) {
		
		String email = userRequest.getEmail();
		
		//Check that not exists user already with that email
		if (userRepository.existsByEmail(email)) {
			throw new UserAlreadyExistsException(email);
		}
		
		//Create user
		User user = new User(
					userRequest.getEmail(),
					userRequest.getUsername(),
					passwordEncoder.encode(userRequest.getPassword())
				);
		
		//Assign roles
		Set<String> strRole = userRequest.getRole();
		Set<Rol> roles = new HashSet<>();
		
		if (strRole == null) {
			Rol userRole = rolRepository.findByRol(ERol.ROLE_USER)
					.orElseThrow(() -> new RolNotFoundException(ERol.ROLE_USER));
			
			roles.add(userRole);
		}else {
			strRole.forEach(role -> {
				Rol userRole;
				switch (role) {
				case "ROLE_ADMIN": 
					userRole = rolRepository.findByRol(ERol.ROLE_ADMIN)
					.orElseThrow(() -> new RolNotFoundException(ERol.ROLE_ADMIN));
					
					roles.add(userRole);
					break;
					
				case "ROLE_MODERATOR": 
					userRole = rolRepository.findByRol(ERol.ROLE_MODERATOR)
					.orElseThrow(() -> new RolNotFoundException(ERol.ROLE_MODERATOR));
					
					roles.add(userRole);
					break;
					
				case "ROLE_USER": 
					userRole = rolRepository.findByRol(ERol.ROLE_USER)
					.orElseThrow(() -> new RolNotFoundException(ERol.ROLE_USER));
					
					roles.add(userRole);
					break;
					
				default:
					throw new RolNotFoundException(role);
				}
			});
			
		}
		
		user.setRol(roles);

		//Save and transform user
		UserDTO userDTO = userMapper.userDTO(userRepository.save(user));
		
		//Return ResponseEntity
		ApiResponse response = new ApiResponse(userDTO);
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.CREATED
				);
	}
	
	public User getUserEntity(String email){
		User user = userRepository.findByEmail(email)
				.orElseThrow(()->new UserNotFoundException(email));

		return user;
	}
	
	public boolean existsUser(String email) {
		return userRepository.existsByEmail(email);
	}
		
}