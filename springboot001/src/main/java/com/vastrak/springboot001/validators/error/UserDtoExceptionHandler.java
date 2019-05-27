package com.vastrak.springboot001.validators.error;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserDtoExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Log logger = LogFactory.getLog(UserDtoExceptionHandler.class);

	/**
	 * MethodArgumentTypeMismatchException: A TypeMismatchException raised while
	 * resolving a controller method argument.
	 * 
	 * @param ex
	 * @return ResponseEntity<UserResponseDtoError>
	 */
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<UserResponseDtoError> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {

		// ERROR DE TIPO, ENVIAN ALGO QUE NO ES LONG.

		// String message = String.format("parameter '%s' must be '%s'", ex.getName(),
		// ex.getRequiredType().getSimpleName());
		// and have value = ex.getValue(); then exception!
		UserResponseDtoError userResponseDtoError = new UserResponseDtoError(HttpStatus.BAD_REQUEST,
				ErrorMessages.USER_UD_PARAMETER_MUST_BE_LONG, ErrorMessages.USER_UD_PARAMETER_MUST_BE_LONG);
		return new ResponseEntity<>(userResponseDtoError, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ UserDtoInvalidIdException.class })
	public ResponseEntity<UserResponseDtoError> handleInvalidIdException(Exception ex, WebRequest request) {

		// ERROR ENVIAN ALGO QUE ES NEGATIVO!

		logger.info(">> capture custom exception UserDtoInvalidIdException");

		UserResponseDtoError userResponseDtoError = new UserResponseDtoError(HttpStatus.BAD_REQUEST,
				ErrorMessages.USER_ID_PARAMETER_MUST_BE_POSITIVE, ex.getMessage());
		return new ResponseEntity<>(userResponseDtoError, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Se produce cuando el campo de un userDto viene mal formateado
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ UserDtoFieldException.class })
	public ResponseEntity<UserResponseDtoError> handleFieldException(Exception ex, WebRequest request) {

		logger.info(">> capture custom exception UserDtoFieldException!!!!");

		UserResponseDtoError userResponseDtoError = new UserResponseDtoError(HttpStatus.BAD_REQUEST, ex.getMessage(),
				((UserDtoFieldException) ex).getErrors());
		return new ResponseEntity<>(userResponseDtoError, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Se produce cuando se pretende hacer un update de un usuario que no existe y
	 * tiene una id no válida.
	 * <p>
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ UserDtoIdNonExistException.class })
	public ResponseEntity<UserResponseDtoError> handleIdNonExistException(Exception ex, WebRequest request) {

		logger.info(">> capture custom exception UserDtoIdNonExistException!!!!");

		UserResponseDtoError userResponseDtoError = new UserResponseDtoError(HttpStatus.BAD_REQUEST, ex.getMessage(),
				((UserDtoIdNonExistException) ex).getError());
		return new ResponseEntity<>(userResponseDtoError, HttpStatus.BAD_REQUEST);
	}

}
