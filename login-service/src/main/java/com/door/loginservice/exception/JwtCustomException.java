package com.door.loginservice.exception;

import org.springframework.http.HttpStatus;

public class JwtCustomException extends RuntimeException {

    public String msg;
    public HttpStatus httpStatus;

    public JwtCustomException(String msg, HttpStatus httpStatus) {
        this.msg = msg;
        this.httpStatus = httpStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return "JwtException [msg=" + msg + ", httpStatus=" + httpStatus + "]";
    }
    
    

}
