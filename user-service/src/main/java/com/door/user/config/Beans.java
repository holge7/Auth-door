package com.door.user.config;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import commons.jwt.JwtUtils;

@Configuration
@ComponentScan({"commons.jwt.JwtUtils", "commons.jwt", "commons", "com.commons.jwt.JwtUtils", "com.commons.jwt", "com.commons"})
public class Beans {
	
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	Mapper mapper() {
		return new Mapper();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	@Bean
	Gson gson() {
		return new Gson();
	}




}
