package com.door.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.door.security.HttpFilter;
import com.door.security.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

	JwtUtil jwtUtil;

	public ResourceController(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	
	@GetMapping("/user/greeting")
	public String helloUsers() {
		return "Hello world for Users auth";
	}
	
	@GetMapping("/mod/greeting")
	public String helloModerator(HttpServletRequest request) throws IOException {
		return "Hello "+HttpFilter.user.getName()+" for Moderator auth";
	}
	
	@GetMapping("/admin/greeting")
	public String hello() {
		return "Hello "+HttpFilter.user.getName()+" for Admin auth";
	}
	
	@GetMapping("/public/greeting")
	public String publicHello() {
		return "Hello "+HttpFilter.user.getName()+" for all users";
	}
	
}