package com.door.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import com.door.utils.ApiResponse;

@ControllerAdvice
public class ExceptionHandlerImpl extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(JwtExceptionn.class)
	public ResponseEntity<ApiResponse> handleJwtException(JwtExceptionn jwtException){
		
		ApiResponse response = new ApiResponse(jwtException);

		return new ResponseEntity<ApiResponse>(
					response,
					jwtException.getHttpStatus()
				);
	}
	
}