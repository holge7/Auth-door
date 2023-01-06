package com.door.loginservice.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.door.loginservice.utils.ApiResponse;
import com.google.gson.Gson;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

    private JwtUtils jwtUtils;
    private RestTemplate restTemplate;
    private Gson gson;

    public JwtAuthFilter(JwtUtils jwtUtils, RestTemplate restTemplate, Gson gson) {
        this.jwtUtils = jwtUtils;
        this.restTemplate = restTemplate;
        this.gson = gson;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("Hello World!!!");
        System.out.println(request);
        System.out.println(request.getParameter("jwt"));
        System.out.println(request.getRequestURL().toString());
        // String jwt = request.getHeader("Authorization");

        // ResponseEntity<String> loginResponse = restTemplate.postForEntity("http://login-service/api/login/valid", jwt, String.class);
        // ApiResponse apiResponse = gson.fromJson(loginResponse.getBody(), ApiResponse.class);

        // if (!apiResponse.getError()) {
            
        //     Authentication auth = jwtUtils.getAuthentication(jwt);

        //     if (auth != null) {
        //         SecurityContext context = SecurityContextHolder.createEmptyContext();
        //         context.setAuthentication(auth);
        //         SecurityContextHolder.setContext(context);
        //         System.out.println(SecurityContextHolder.getContext());
        //     }
        //     logger.info("Valid JWT");
        // } else {
        //     logger.warn("Invalid JWT");
        // }
        
        filterChain.doFilter(request, response);
    }
    
}
