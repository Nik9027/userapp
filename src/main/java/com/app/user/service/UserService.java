package com.app.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.app.user.model.User;


@Service
public abstract class UserService {
	
	public abstract User adduser(String fname, String lname, String email, String pincode, String birthdate);
	public abstract User updateuser(String id, String pincode, String birthdate);
	public abstract List<User> deleteuser(String userid);
	public abstract boolean doesUserExist(String userid);
	public abstract boolean checkEmailAlreadyExist(String email);
	public abstract List<User> getCurrentMonthUserList();
	public abstract Map<String, Integer> getMonthlyUserCount();
}
