package com.vastrak.springboot001.validators.error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class UserResponseDtoError {
	
    private HttpStatus status;
    private String message;
    private List<String> errors;
    
    
    public UserResponseDtoError() {
    	
    }
    
    /**
     * 
     * @param status the HTTP status code
     * @param message the error message associated with exception
     * @param errors List of constructed error messages
     */
 
    public UserResponseDtoError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
 
    /**
     * 
     * @param status the HTTP status code
     * @param message the error message associated with exception
     * @param error String constructed error message
     */
    
    public UserResponseDtoError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public void addError(String error) {
		if(errors == null) {
			errors = new ArrayList<>();
		}
	    errors.add(error);
	}
    
}
