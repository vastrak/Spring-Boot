package com.vastrak.springboot001.validators.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class UserDtoExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({UserDtoIdException.class})
	public ResponseEntity<String> handleUserDtoIdException() {
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
}
