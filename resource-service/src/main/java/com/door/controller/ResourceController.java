package com.door.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

	@GetMapping("/private/greeting")
	public String hello() {
		return "Hello world for Users auth";
	}
	
	@GetMapping("/public/greeting")
	public String publicHello() {
		return "Hello world for all users";
	}
	
}
