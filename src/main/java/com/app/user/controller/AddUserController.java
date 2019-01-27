package com.app.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.user.exceptionHandler.UserEmailAlreadyExistException;
import com.app.user.exceptionHandler.UserValidationException;
import com.app.user.model.ConstantMessages;
import com.app.user.model.ConstantsValues;
import com.app.user.model.ErrorMessage;
import com.app.user.model.User;
import com.app.user.service.UserService;
import com.app.user.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin

@RequestMapping("/app/user")
public class AddUserController {
	
	Logger logger= LoggerFactory.getLogger(AddUserController.class);    
	@Autowired
	UserService service;
	
	@Autowired
	UserUtils util;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> adduser(@RequestParam(value=ConstantsValues.FNAME, required=false) String fname, 
			@RequestParam(value=ConstantsValues.LNAME, required=false) String lname,
			@RequestParam(value=ConstantsValues.EMAIL, required=false) String email, 
			@RequestParam(value=ConstantsValues.PINCODE, required=false) String pinCode, 
			@RequestParam(value=ConstantsValues.BIRTHDATE, required=false) String birthDate) {
		
		
		List<ErrorMessage> errormsg= new ArrayList<ErrorMessage>();
				
		if (fname == null || fname.isEmpty()) {
			logger.error(fname);
			errormsg.add(new ErrorMessage(ConstantMessages.C50001,ConstantsValues.FNAME, ConstantMessages.M50001));
		}
		
		if (lname == null || lname.isEmpty()) {
			logger.error(lname);
			errormsg.add(new ErrorMessage(ConstantMessages.C50002,ConstantsValues.LNAME, ConstantMessages.M50002));
		}
		
		if (email == null || email.isEmpty() || !email.contains("@")) {
			logger.error(email);
			errormsg.add(new ErrorMessage(ConstantMessages.C50003,ConstantsValues.EMAIL, ConstantMessages.M50003));
		}
		if(email != null && service.checkEmailAlreadyExist(email))
		{
			logger.error("this email id already exist.");
			throw new UserEmailAlreadyExistException(ConstantMessages.V20002,email,errormsg);
		}
		
		if (birthDate == null || birthDate.isEmpty()) {
			logger.error(birthDate);
			errormsg.add(new ErrorMessage(ConstantMessages.C50005,ConstantsValues.BIRTHDATE, birthDate+" "+ConstantMessages.M50005));
		}
		
		if (pinCode == null || pinCode.isEmpty()) {
			logger.error(pinCode);
			errormsg.add(new ErrorMessage(ConstantMessages.C50004,ConstantsValues.PINCODE, pinCode+" "+ConstantMessages.M50004));
		}
		
		if(errormsg.size()>0)
		{
			logger.error("Validation error occured ");
			throw new UserValidationException(ConstantMessages.V20001, errormsg);
		}
		
		User user=service.adduser(fname, lname, email, pinCode, birthDate);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
}
