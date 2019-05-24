package com.vastrak.springboot001.validators;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import com.vastrak.springboot001.dto.UserDto;

public class UserDtoValidatorTest {

	private static final Log logger = LogFactory.getLog(UserDtoValidatorTest.class);

	@Test
	public void userIdValidatorTest() {

		boolean error = UserDtoValidator.isPositiveLongId(-5L);
		Assert.assertFalse(error);
	}

	@Test
	public void validateNewUserTest() {

		UserDto userDto = new UserDto();
		userDto.setUserDto_id(5L); // new userDto_id must be null;
		userDto.setName("Luis");
		userDto.setEmail("luis@notengowifi.com");

		Assert.assertTrue(UserDtoValidator.validateNewUser(userDto).size() == 1);

		userDto = new UserDto();
		userDto.setUserDto_id(null);
		userDto.setName(""); // incompleted field
		userDto.setEmail("luis@notengowifi.com");

		Assert.assertTrue(UserDtoValidator.validateNewUser(userDto).size() == 1);

		userDto = new UserDto();
		userDto.setUserDto_id(null);
		userDto.setName("Luis");
		userDto.setEmail("luisnotengowifi.com"); // It is not a valid email.

		Assert.assertTrue(UserDtoValidator.validateNewUser(userDto).size() == 1);

		userDto = new UserDto();
		userDto.setUserDto_id(-5L); // must be null
		userDto.setName(null); // must be not null
		userDto.setEmail(null); // must be not null

		Assert.assertTrue(UserDtoValidator.validateNewUser(userDto).size() == 2);

	}

}
