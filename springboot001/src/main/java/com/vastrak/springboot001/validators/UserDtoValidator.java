package com.vastrak.springboot001.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vastrak.springboot001.dto.UserDto;
import com.vastrak.springboot001.validators.error.ErrorMessages;

public class UserDtoValidator {
	
	private static final Log logger = LogFactory.getLog(UserDtoValidator.class);
	
	public static final Pattern VALID_EMAIL = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	/**
	 * 
	 * @param Long userDto_id
	 * @return boolean true if userDto_id != null and userDto_id > 0
	 */
	public static boolean isPositiveLongId(Long userDto_id) {
		
		return (userDto_id != null) && (userDto_id > 0) ;
	}
	
	
	
	/**
	 * 
	 * @param userDto
	 * @return true if the fields name and email are well formatted.
	 */
	public static boolean validNameAndEmail(UserDto userDto) {
		
		boolean validName = (userDto.getName()!=null && !userDto.getName().isEmpty());
		boolean validEmail =(userDto.getEmail()!=null && VALID_EMAIL.matcher(userDto.getEmail()).find());
		return  validName && validEmail;
	}

	
	/**
	 * Validate an UserDto for a new user.
	 * @param userDto with userDto_id field == null;
	 * @return List<String> errors list
	 */
	public static List<String> validateNewUser(UserDto userDto) {
	
		List<String> errors = new ArrayList<>();
		if(userDto.getUserDto_id() != null) {
			errors.add(ErrorMessages.USERDTO_ID_MUST_BE_NULL);
		}
		if(!validNameAndEmail(userDto)) {
			errors.add(ErrorMessages.USERDTO_FIELD_WRONG_FORMAT);
		}
		return errors;
	}
	
	/**
	 * Validate an UserDto for a update user. (formated fields only)
	 * @param userDto with userDto_id field != null
	 * @return List<String> errros list o an empty list.
	 */
	public static List<String> validateUpdateUser(UserDto userDto) {

		List<String> errors = new ArrayList<>();
		if(userDto.getUserDto_id() == null) {  // update users require id field not null
			errors.add(ErrorMessages.USERDTO_ID_MUST_NOT_BE_NULL);
		}
		if(!validNameAndEmail(userDto)) {
			errors.add(ErrorMessages.USERDTO_FIELD_WRONG_FORMAT);
		}
		return errors;		
	}

}
