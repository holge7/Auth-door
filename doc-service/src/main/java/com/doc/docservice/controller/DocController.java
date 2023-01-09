package com.doc.docservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doc.docservice.service.DocService;

import commons.utils.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/doc")
public class DocController {
	
	public DocService docService;
	
	public DocController(DocService docService) {
		this.docService = docService;
	}
	
	
	@GetMapping("")
	public ResponseEntity<ApiResponse>  swaggerTest() {
		return new ResponseEntity<ApiResponse>(
				docService.getSwaggerURLs(),
				HttpStatus.OK
			);
	}
	
	
	
}
