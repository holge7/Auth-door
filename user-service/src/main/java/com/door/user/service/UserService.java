package com.door.user.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.door.user.data.ERol;
import com.door.user.data.payload.request.LoginRequest;
import com.door.user.data.payload.request.SingupRequest;
import com.door.user.entity.Rol;
import com.door.user.entity.User;
import com.door.user.exception.RolNotFoundException;
import com.door.user.exception.UserAlreadyExistsException;
import com.door.user.exception.UserNotFoundException;
import com.door.user.mapper.UserMapper;
import com.door.user.repository.RolRepository;
import com.door.user.repository.UserRepository;

import commons.dto.UserDTO;
import commons.utils.ApiResponse;

@Service
public class UserService {
	
	public UserRepository userRepository;
	public RolRepository rolRepository;
	public PasswordEncoder passwordEncoder;
	public UserMapper userMapper;
	
	public UserService(UserRepository userRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder,
			UserMapper userMapper) {
		this.userRepository = userRepository;
		this.rolRepository = rolRepository;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
	}
	
	
	public ApiResponse login(LoginRequest userLogin){
		
		String email = userLogin.getEmail();
		
		//Validate the existence of the user/password
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new UserNotFoundException(email));

		System.out.println("User");
			System.out.println(user);

		UserDTO userDTO = userMapper.userDTO(user);
		
			System.out.println("User dto");
			System.out.println(userDTO);

			return new ApiResponse(userDTO);

	}
	
	
	public ApiResponse register(SingupRequest userRequest) {
		
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
		Set<Rol> rolesToAdd = userRequest.getRole();
		Set<Rol> rolesChecked = new HashSet<>();
		
		if (rolesToAdd == null) {
			Rol userRole = rolRepository.findByRol(ERol.ROLE_USER)
					.orElseThrow(() -> new RolNotFoundException(ERol.ROLE_USER));
			
					rolesChecked.add(userRole);
		} else {

			for (Rol role : rolesToAdd) {
				// Get rol and assert that exists, then add to list
				Optional<Rol> userRole = rolRepository.findByRol(role.getRol());

				if (!userRole.isPresent()) {
					throw new RolNotFoundException(role.rol);
				}

				rolesChecked.add(userRole.get());
			}
		}
		
		user.setRol(rolesChecked);

		//Save and transform user
		UserDTO userDTO = userMapper.userDTO(userRepository.save(user));
		
		//Return ResponseEntity
		return new ApiResponse(userDTO);
	}
		
}