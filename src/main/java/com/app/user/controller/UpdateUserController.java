package com.app.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.user.exceptionHandler.UserValidationException;
import com.app.user.model.ConstantMessages;
import com.app.user.model.ConstantsValues;
import com.app.user.model.ErrorMessage;
import com.app.user.model.User;
import com.app.user.service.UserService;



@RestController
@CrossOrigin

@RequestMapping(value="/app/user")
public class UpdateUserController {

	Logger logger= LoggerFactory.getLogger(UpdateUserController.class);
	@Autowired
	UserService service;
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<?> updateuserdata(
			@RequestParam(value=ConstantsValues.ID, required=false) String userID,
			@RequestParam(value=ConstantsValues.BIRTHDATE, required=false) String birthDate,
			@RequestParam(value=ConstantsValues.PINCODE, required=false) String pinCode
			){
		List<ErrorMessage> errormsg= new ArrayList<ErrorMessage>();
		
		if(userID == null || userID.isEmpty())
		{
			logger.error(userID);
			errormsg.add(new ErrorMessage(ConstantMessages.C50006,ConstantsValues.BIRTHDATE, ConstantMessages.M50006));
		}
		if(birthDate == null && pinCode == null) {
			logger.error("birthdate "+birthDate);
			logger.error("pincode "+pinCode);
			errormsg.add(new ErrorMessage(ConstantMessages.C50007,ConstantsValues.BIRTHDATE+","+ConstantsValues.PINCODE, ConstantMessages.M50007));
		}
		
		if (errormsg.size() > 0)
		{
			logger.error("Validation error occured");
			throw new UserValidationException(ConstantMessages.V20006, errormsg);
		}
		User user=service.updateuser(userID, pinCode, birthDate);
		ResponseEntity<?> response= new ResponseEntity<User>(user, HttpStatus.OK);
		
		return response;
	}
}
