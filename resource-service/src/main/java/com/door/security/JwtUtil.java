package com.door.security;

import org.springframework.stereotype.Component;

import com.door.dto.UserDTO;
import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

    Gson gson;

	public JwtUtil() {}

    public JwtUtil(Gson gson) {
        this.gson = gson;
    }

    /**
	 * Get all info about a JWT
	 * @param jwt
	 * @return
	 */
    public UserDTO getUserFromJwt(String jwt) {
        // Get JWT payload
        int i = jwt.lastIndexOf('.');
        String withoutSignature = jwt.substring(0, i+1);
        Jwt<Header,Claims> untrusted = Jwts.parser().parseClaimsJwt(withoutSignature);

        // Get userDTO from JWT payload
        String userString = untrusted.getBody().getSubject(); 
        System.out.println(userString);
        // Parse user jwt data json to UserDTO
        UserDTO userDTO = gson.fromJson(userString, UserDTO.class);
        System.out.println(userDTO);
        return userDTO;
    }
}
