package com.vastrak.springboot001.validators.error;

import java.util.List;

public class UserDtoFieldException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private List<String> errors;
	
	public UserDtoFieldException(String message, List<String> errors) {
		super(message);
		this.errors = errors; 
	}
	
	public List<String> getErrors() {
		return errors;
	}

}
