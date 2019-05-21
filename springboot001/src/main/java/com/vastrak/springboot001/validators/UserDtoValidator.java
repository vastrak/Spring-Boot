package com.vastrak.springboot001.validators;


public class UserDtoValidator {
	
	/**
	 * 
	 * @param Long userDto_id
	 * @return boolean true if userDto_id != null and userDto_id > 0
	 */
	
	public static boolean legalUserDtoId(Long userDto_id) {
		
		return (userDto_id != null) && (userDto_id > 0) ;
	}
	

}
