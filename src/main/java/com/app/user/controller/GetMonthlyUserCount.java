package com.app.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
@RequestMapping(value="/app/user/count")
public class GetMonthlyUserCount {

	@Autowired
	UserService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getMonthlyUserCounts(){
		
		Map<String, Integer> usercount=service.getMonthlyUserCount();		
		return new ResponseEntity<Map<String, Integer>>(usercount, HttpStatus.OK);
		
	}
}
