package com.vastrak.springboot001.validators;

import org.junit.Assert;
import org.junit.Test;


public class UserDtoValidatorTest {
	
	@Test
	public void userIdValidatorTest() {
		
		 boolean error = UserDtoValidator.legalUserDtoId(-5L);
		 Assert.assertTrue(error);
	}

}
