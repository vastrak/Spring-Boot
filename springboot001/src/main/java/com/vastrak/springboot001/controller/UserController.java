package com.vastrak.springboot001.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vastrak.springboot001.dto.UserDto;
import com.vastrak.springboot001.dto.UserResponseDto;
import com.vastrak.springboot001.service.UserService;
import com.vastrak.springboot001.validators.UserDtoValidator;
import com.vastrak.springboot001.validators.error.ErrorMessages;
import com.vastrak.springboot001.validators.error.UserDtoFieldException;
import com.vastrak.springboot001.validators.error.UserDtoIdNonExistException;
import com.vastrak.springboot001.validators.error.UserDtoInvalidIdException;

/**
 * 
 * @author Christian
 *
 */

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Log logger = LogFactory.getLog(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * Retrieve a user by id
	 * @param user_id
	 * @return
	 * @throws UserDtoInvalidIdException
	 */
	@GetMapping(path = "/{user_id}")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable(value = "user_id") Long user_id) throws Exception {

		boolean legal = UserDtoValidator.isPositiveLongId(user_id);
		if (!legal) {
			throw new UserDtoInvalidIdException(ErrorMessages.USER_ID_PARAMETER_MUST_BE_POSITIVE);
		}
		UserDto userDto = userService.getUserById(user_id);
		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.addUserDto(userDto);
		return new ResponseEntity<>(userResponseDto, HttpStatus.OK);

	}

	/**
	 * Retrieve all stored users 
	 * @return
	 */

	@GetMapping()
	public ResponseEntity<UserResponseDto> getUsers() {

		UserResponseDto userResponseDto = new UserResponseDto(userService.getUsers());
		return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
	}

	/**
	 * Create a new user
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@PostMapping()
	public ResponseEntity<UserResponseDto> createUser(@RequestBody UserDto userDto) throws Exception {

		List<String> errors = UserDtoValidator.validateNewUser(userDto);
		if (!errors.isEmpty()) {
			throw new UserDtoFieldException(ErrorMessages.USERDTO_FIELD_ERROR, errors);
		}
		userDto.setUserDto_id(userService.createUpdateUser(userDto));
		userDto.setArticles(0); // why? or null?
		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.addUserDto(userDto);
		logger.info(">>> new user "+userDto);
		return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
	}

	/**
	 * Update an user
	 * @param userDto_id must be not null and exist
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@PutMapping(path = "/{userDto_id}")
	public ResponseEntity<UserResponseDto> updateUser(@PathVariable(value = "userDto_id") Long userDto_id,
			@RequestBody UserDto userDto) throws Exception {

		boolean legal = UserDtoValidator.isPositiveLongId(userDto_id);
		if ((!legal) || (userDto.getUserDto_id()!=userDto_id)) {
			throw new UserDtoInvalidIdException(ErrorMessages.USER_ID_PARAMETER_MUST_BE_POSITIVE);
		}
		
		List<String> errors = UserDtoValidator.validateUpdateUser(userDto);
		// verifico si hay errores de formato
		if (!errors.isEmpty()) {
			throw new UserDtoFieldException(ErrorMessages.USERDTO_FIELD_ERROR, errors);

		}

		// verificamos que se quiere hacer un update de un usuario que existe
		if (!userService.existUser(userDto.getUserDto_id())) {
			throw new UserDtoIdNonExistException(ErrorMessages.USERDTO_NON_EXIST,
					ErrorMessages.USERDTO_ID_DOES_NOT_EXIST);
		}

		userService.createUpdateUser(userDto);
		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.addUserDto(userDto);
		return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
	}
	
	/**
	 * Delete an user
	 * @param userDto_id must be not null and exists
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(path ="/{userDto_id}")
	public ResponseEntity<UserResponseDto> removeUser(@PathVariable(value = "userDto_id") Long userDto_id) throws Exception {
	
		boolean legal = UserDtoValidator.isPositiveLongId(userDto_id);
		if (!legal) {
			throw new UserDtoInvalidIdException(ErrorMessages.USER_ID_PARAMETER_MUST_BE_POSITIVE);
		}
		userService.removeUser(userDto_id);
		UserResponseDto userResponseDto = new UserResponseDto();
		return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
	}

}
