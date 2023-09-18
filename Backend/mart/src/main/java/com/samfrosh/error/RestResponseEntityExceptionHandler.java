package com.samfrosh.error;

import com.samfrosh.exception.CartError;
import com.samfrosh.exception.ProductNotFound;
import com.samfrosh.exception.UserExits;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ResponseStatus
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ErrorMsg> productnotfound(ProductNotFound productNotFound) {
        ErrorMsg errorMsg = new ErrorMsg(HttpStatus.NOT_FOUND, productNotFound.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
    }

    @ExceptionHandler(UserExits.class)
    public ResponseEntity<ErrorMsg> UserExits(UserExits userExits) {
        ErrorMsg errorMsg = new ErrorMsg(HttpStatus.NOT_FOUND, userExits.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
    }

    @ExceptionHandler(JwtTokenExpiredException.class)
    public ResponseEntity<ErrorMsg> handleJwtTokenExpiredException(JwtTokenExpiredException ex) {
        ErrorMsg apiError = new ErrorMsg(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    @ExceptionHandler(CartError.class)
    public ResponseEntity<ErrorMsg> cartError(CartError ex) {
        ErrorMsg apiError = new ErrorMsg(HttpStatus.OK, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(apiError);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorMsg> jwtexpired(MalformedJwtException ex) {
        ErrorMsg apiError = new ErrorMsg(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(apiError);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorMsg> jwtexpired2(ExpiredJwtException ex) {
        ErrorMsg apiError = new ErrorMsg(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(apiError);
    }
}
