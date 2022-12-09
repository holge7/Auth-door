package com.door.configuration;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OncePerRequest extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.info("==================");
		logger.info("Once per Request");
		logger.info("==================");
		
		System.out.println("==================");
		System.out.println("Once per Request");
		System.out.println("==================");
		
		filterChain.doFilter(request, response);
	}

}
