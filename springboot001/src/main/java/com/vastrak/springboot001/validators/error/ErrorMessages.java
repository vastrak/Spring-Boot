package com.vastrak.springboot001.validators.error;

public interface ErrorMessages {
	
	public static final String USER_ID_PARAMETER_MUST_BE_POSITIVE = "Parameter user_id must be positive";
	public static final String USER_UD_PARAMETER_MUST_BE_LONG = "Parameter user_id must be a Long";
	//
	public static final String USERDTO_ID_MUST_BE_NULL = "The userDto_id field must be null for new users";
	public static final String USERDTO_FIELD_WRONG_FORMAT = "The name or/and email fields are badly formatted, empty or null";
	public static final String USERDTO_FIELD_ERROR = "UserDto field error";
	
}
