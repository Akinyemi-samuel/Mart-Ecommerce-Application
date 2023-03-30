package com.samfrosh.error;


public class JwtTokenExpiredException extends RuntimeException{

    public JwtTokenExpiredException(String message) {
        super(message);
    }
}
