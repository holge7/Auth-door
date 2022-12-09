package com.door.auth.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.door.auth.data.entity.User;
import com.door.auth.data.repository.UserRepository;
import com.door.auth.exception.UserNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// i am using load by email
		return null;
	}
	
	public UserDetails loadUserByEmail(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException(email));
		 
		return UserDetailsImpl.build(user);
	}

}
