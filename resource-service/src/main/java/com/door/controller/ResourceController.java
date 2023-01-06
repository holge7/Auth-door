package com.door.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

	@GetMapping("/test")
	public String helloTest() {
		return "Hello to test";
	}

	@GetMapping("/user/greeting")
	public String helloUsers() {
		return "Hello world for Users auth";
	}
	
	@GetMapping("/mod/greeting")
	public String helloModerator(HttpServletRequest request) throws IOException {
		return "Hello for Moderator auth";
	}
	
	@GetMapping("/admin/greeting")
	public String hello() {
		return "Hello for Admin auth";
	}
	
	@GetMapping("/public/greeting")
	public String publicHello() {
		return "Hello for all users";
	}
	
}