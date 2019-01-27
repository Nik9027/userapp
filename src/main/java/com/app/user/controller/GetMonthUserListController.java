package com.app.user.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.app.user.model.ErrorMessage;
import com.app.user.model.User;
import com.app.user.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(value="/app/user")
public class GetMonthUserListController {

	@Autowired
	UserService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getCurrentMonthUserList(){
		
		List<User> userlist=service.getCurrentMonthUserList();		
		return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
		
	}
}
