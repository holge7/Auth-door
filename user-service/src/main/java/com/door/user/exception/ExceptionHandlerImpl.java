package com.door.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import commons.utils.ApiResponse;


@ControllerAdvice
public class ExceptionHandlerImpl extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> handleException(Exception e){

		ApiResponse response = new ApiResponse(e.getMessage());

		return new ResponseEntity<ApiResponse>(
			response, 
			HttpStatus.INTERNAL_SERVER_ERROR
		);

	}
	
	@ExceptionHandler(RolException.class)
	public ResponseEntity<ApiResponse> handleRolException(RolException RolException){
		
		ApiResponse response = new ApiResponse(RolException.getMsg());

		return new ResponseEntity<ApiResponse>(
					response,
					RolException.httpStatus
				);
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ApiResponse> handleUserException(UserException userException){
		
		ApiResponse response = new ApiResponse(userException.getMsg());
		
		return new ResponseEntity<ApiResponse>(
					response,
					userException.httpStatus
				);
	}
	
}