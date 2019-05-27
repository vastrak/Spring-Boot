package com.vastrak.springboot001.validators.error;

public class UserDtoIdNonExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String error;
	
	public UserDtoIdNonExistException(String message, String error) {
		super(message);
		this.error = error; 
	}
	
	public String getError() {
		return error;
	}

}
