package com.door.security;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;

import com.door.dto.UserDTO;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
/* 
 * Class to filter all http request
 */
@Configuration
public class HttpFilter implements Filter {

    public static UserDTO user = new UserDTO();
    public static boolean isAuth = false;

    JwtUtil jwtUtil;

    public HttpFilter() {}

    public HttpFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        getUser(request);

        chain.doFilter(request, response);
    }

    /*
     * Get user from a Authorization Header (jwt)
     */
    public void getUser(ServletRequest request) {
        // Get auth user from jwt
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String jwt = httpRequest.getHeader("authorization"); 

        // We pass as auth user
        if (jwt != null) {
            // Set user in static user
            user = jwtUtil.getUserFromJwt(jwt);
            isAuth = true;
        }

    }
    
}
