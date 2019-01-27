package com.app.user.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
	
	@ExceptionHandler(UserValidationException.class)
	public ResponseEntity<?> handlevalidationerror(UserValidationException e){
		
		return new ResponseEntity<BusinessException>(new BusinessException(e.getResMsg(), e.getValErrors(), e.getUserid()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserEmailAlreadyExistException.class)
	public ResponseEntity<?> handlevalidationerror(UserEmailAlreadyExistException e){
		return new ResponseEntity<BusinessException>(new BusinessException(e.getResMsg(), e.getValErrors(), "N.A"), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handlevalidationerror(UserNotFoundException e){
		
		return new ResponseEntity<BusinessException>(new BusinessException(e.getResMsg(), e.getValErrors(), e.getUserid()), HttpStatus.BAD_REQUEST);
	}
	
	
}
