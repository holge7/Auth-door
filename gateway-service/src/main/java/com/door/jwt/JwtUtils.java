package com.door.jwt;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.door.dto.UserDTO;
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

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${security.app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${security.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	public String generateJwtToken(UserDTO userDTO) {		
		System.out.println("CREO JWT");
		System.out.println("===================");
		System.out.println(userDTO.getEmail());
		System.out.println(userDTO.getRol());
		System.out.println("===================");
		JwtData jwtData = new JwtData(userDTO.getEmail(), userDTO.getRol().toString());
		
		Gson gson = new Gson();
		String jwtDataString = gson.toJson(jwtData);
		
		return Jwts.builder()
				.setSubject(jwtDataString)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	public String getEmailNameFromJwtToken(String token) {
		return Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
    public Jws<Claims> getAllClaimsFromToken(String authToken) {
        //Change secret to signing key from auth service.
        return  Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(authToken);
    }
    
    /*public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
    }*/
	
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException  e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		}catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		} catch (Exception e) {
			logger.error("General error {}", e.getMessage());
		}
		
		return false;
	}
	
	
}
