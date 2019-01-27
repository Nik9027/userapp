package com.app.user.service;

import java.security.Principal;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jws.soap.SOAPBinding.Use;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.user.exceptionHandler.UserNotFoundException;
import com.app.user.exceptionHandler.UserValidationException;
import com.app.user.model.ConstantMessages;
import com.app.user.model.ConstantsValues;
import com.app.user.model.ErrorMessage;
import com.app.user.model.User;
import com.app.user.repository.UserRepository;
import com.app.user.utils.UserUtils;

import java.util.Calendar;

@Component
public class UserServiceImpl extends UserService {

	Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository repository;
	
	@Autowired
	UserUtils util;
	
	@Override
	public User adduser(String fname, String lname, String email, String pincode, String birthdate) {
		logger.info("Request to add user: "+fname+" "+lname+" "+email+" "+pincode+" "+birthdate);
		List<ErrorMessage> errormessage= new ArrayList<ErrorMessage>();
		if(!util.isDateValid(birthdate)) {
			errormessage.add(new ErrorMessage(ConstantMessages.C50005, ConstantsValues.BIRTHDATE, ConstantMessages.M50005));
		}
		if(!util.isPinCodeValid(pincode)) {
			errormessage.add(new ErrorMessage(ConstantMessages.C50004, ConstantsValues.PINCODE, ConstantMessages.M50004));
		}
		if(errormessage.size()>0)
		{
			logger.error("Error occured while adding the user.");
			throw new UserValidationException(ConstantMessages.V20001, errormessage);
		}
		User user= new User(fname, lname, email, pincode, birthdate);
		return repository.adduser(user);
	}
	
	@Override
	public List<User> deleteuser(String userid) {
		
		logger.info("request to delete a user with id:",userid);
		if(!repository.doesuserexist(userid))
		{
			logger.error("Error occured while deleteing user as user is "+userid);
			throw new UserNotFoundException(ConstantMessages.V20003, userid, new ArrayList<ErrorMessage>());
		}
			
		return repository.deleteuser(userid);
		
		
	}
	
	@Override
	public User updateuser(String id, String pincode, String birthdate) {
		
		logger.info("request to update user with id:"+id);
		if (repository.doesuserexist(id)) {
			
			User userdata= repository.getuserbyid(id);
			List<ErrorMessage> errormsglist= new ArrayList<ErrorMessage>();
			if(pincode != null && !pincode.isEmpty())
			{
				if(util.isPinCodeValid(pincode)) {
					userdata.setPincode(pincode);
				}
				else {
					logger.error("Pincode not valid:"+pincode);
					errormsglist.add(new ErrorMessage(ConstantMessages.C50004, ConstantsValues.PINCODE, ConstantMessages.M50004));
				}
			}
			if(birthdate != null && !birthdate.isEmpty())
			{
				if(util.isDateValid(birthdate)) {
					userdata.setBirthdate(birthdate);
				}
				else {
					logger.error("birthdate not valid"+birthdate);
					errormsglist.add(new ErrorMessage(ConstantMessages.C50005, ConstantsValues.BIRTHDATE, ConstantMessages.M50005));
				}
			}
			
			if(errormsglist.size()>0) {
				
				throw new UserValidationException(ConstantMessages.V20006, id, errormsglist);
			}
		}
		else {
			logger.error("User not found with id "+id);
			throw new UserNotFoundException(ConstantMessages.V20003, id, new ArrayList<ErrorMessage>());
		}
		return repository.getuserbyid(id);
	}
	
	@Override
	public boolean doesUserExist(String userid) {
		return repository.doesuserexist(userid);
	}

	@Override
	public boolean checkEmailAlreadyExist(String email) {
		List<User> userlist=repository.getalluser();
		Boolean emailexistance= Boolean.FALSE;
		for (User user : userlist) {
			if(user.getEmail().equalsIgnoreCase(email))
			{
				emailexistance=Boolean.TRUE;
				break;
			}
			
		}
		return emailexistance;
		
	}

	@Override
	public List<User> getCurrentMonthUserList() {
		try {
		Calendar cal = Calendar.getInstance();
		int currentMonth=cal.get(Calendar.MONTH);
		
		List<User> alluserlist= repository.getallactiveuser();
		List<User> list= new ArrayList<User>();
		SimpleDateFormat sdf= new SimpleDateFormat(ConstantsValues.userdateformat);
		for (User user : alluserlist) {
			
			Date date= sdf.parse(user.getBirthdate());
			cal.setTime(date);
			int usermonth=cal.get(Calendar.MONTH);
			
			if(currentMonth == usermonth) {
				list.add(user);
				
				}
			}
		return list;
		}
		catch (ParseException e) {
			logger.error("error occured while parsing the dates in getCurrentMonthUserList");
			throw new UserValidationException(ConstantMessages.V20005, ConstantsValues.NA, new ArrayList<>());
		}
		
	
	}

	@Override
	public Map<String, Integer> getMonthlyUserCount() {
		try {
			Map<String, Integer> map= new LinkedHashMap<String, Integer>();
			Calendar cal = Calendar.getInstance();
			DateFormatSymbols dfs = new DateFormatSymbols();
			SimpleDateFormat sdf= new SimpleDateFormat(ConstantsValues.userdateformat);
	        String[] months = dfs.getMonths();
	        
	        
	        Integer[] counter=new Integer[12];
	        for (int i = 0; i < counter.length; i++) {
				counter[i]=0;
				
			}
	        List<User> list= repository.getallactiveuser();
	        for (User user : list) {
	        	Date date= sdf.parse(user.getBirthdate());
				cal.setTime(date);
				int usermonth=cal.get(Calendar.MONTH);
				counter[usermonth]+=1;
			}
	        for (int j=0; j< counter.length; j++) {
	        	map.put(months[j], counter[j]);
			}
			return map;
		}
		catch (ParseException e) {
			logger.error("error occured while parsing the dates in getMonthlyUserCount");
			throw new UserValidationException(ConstantMessages.V20005, ConstantsValues.NA, new ArrayList<>());
		}
		
	}
	

}
