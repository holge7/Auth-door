package com.door.jwt;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.door.dto.UserDTO;
import com.door.exception.JwtExceptionn;
import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.slf4j.Logger;

/**
 * Class to manage all JWT operations
 * @author Holge
 *
 */
@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${security.app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${security.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	/**
	 * Generate JWT 
	 * @param userDTO
	 * @return
	 */
	public String generateJwtToken(UserDTO userDTO) {		

		JwtData jwtData = new JwtData(userDTO.getEmail(), userDTO.getName(), userDTO.getRol());
		
		Gson gson = new Gson();
		String jwtDataString = gson.toJson(jwtData);
		
		return Jwts.builder()
				.setSubject(jwtDataString)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	/**
	 * Get all info about a JWT
	 * @param authToken
	 * @return
	 */
    public Jws<Claims> getAllClaimsFromToken(String authToken) {
        //Change secret to signing key from auth service.
        return  Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(authToken);
    }
	
    /**
     * Validate JWT
     * @param authToken
     * @return
     */
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException  e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
			throw new JwtExceptionn(e);
		}catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
			throw new JwtExceptionn(e);
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
			throw new JwtExceptionn(e);
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
			throw new JwtExceptionn(e);
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
			throw new JwtExceptionn(e);
		} catch (Exception e) {
			logger.error("General error {}", e.getMessage());
			throw new JwtExceptionn(e);
		}
	
	}
	
	
}
