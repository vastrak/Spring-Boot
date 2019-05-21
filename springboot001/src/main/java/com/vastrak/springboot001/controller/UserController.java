package com.vastrak.springboot001.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vastrak.springboot001.dto.UserDto;
import com.vastrak.springboot001.service.UserService;
import com.vastrak.springboot001.validators.UserDtoValidator;
import com.vastrak.springboot001.validators.error.UserDtoIdException;

/**
 * 
 * @author Christian
 *
 */

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 
	 * @param user_id
	 * @return
	 * @throws UserDtoIdException
	 */
	@GetMapping(path = "/{user_id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(value = "user_id") Long user_id) throws UserDtoIdException {
		
		boolean legal = UserDtoValidator.legalUserDtoId(user_id);
		if(!legal) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		UserDto userDto = userService.getUserById(user_id);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
		
	}
	
}
