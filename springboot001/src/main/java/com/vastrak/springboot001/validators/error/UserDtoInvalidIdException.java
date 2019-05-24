package com.vastrak.springboot001.validators.error;

public class UserDtoInvalidIdException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserDtoInvalidIdException(String message) {
		super(message);
	}
	
}
