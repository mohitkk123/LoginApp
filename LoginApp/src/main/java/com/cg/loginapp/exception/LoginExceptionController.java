package com.cg.loginapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class LoginExceptionController {

@ExceptionHandler(value=LoginException.class)
	
	public ResponseEntity<Object> handleException(LoginException exception){
		
		return new ResponseEntity(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
}
