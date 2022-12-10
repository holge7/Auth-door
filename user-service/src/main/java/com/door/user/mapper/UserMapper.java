package com.door.user.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.door.user.data.dto.UserDTO;
import com.door.user.data.dto.UserRegisterDTO;
import com.door.user.entity.User;

@Component
public class UserMapper {
	
	private ModelMapper modelMapper;
	
	public UserMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public UserDTO userDTO(User user) {
		return modelMapper.map(user, UserDTO.class);
	}
	
	public UserRegisterDTO userRegisterDTO(User user) {
		return modelMapper.map(user, UserRegisterDTO.class);
	}
	
}
