package com.door.loginservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.door.loginservice.utils.ApiResponse;

@ControllerAdvice
public class ExceptionHandlerImpl extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerImpl.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e) {
        log.error(e.toString());
        ApiResponse response = new ApiResponse(e.getMessage());
        return new ResponseEntity<>(
            response,
            HttpStatus.INTERNAL_SERVER_ERROR
        );

    }



}
