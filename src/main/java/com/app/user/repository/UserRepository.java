package com.app.user.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.app.user.model.User;


@Repository
public class UserRepository {

	private static Map<String, User> map=new HashMap<String, User>();
	private static int id=1001;
	
	public User adduser(User newuser) {
		String id=getid();
		newuser.setUserID(id);
		map.put(id, newuser);
		return map.get(id);
		
	}
	public User getuser(String userid) {
		
		return map.get(userid);
		
	}
	public User updateuser(User user) {
		map.put(user.getUserID(), user);
		return map.get(user.getUserID());
	}
	public List<User> deleteuser(String userid) {
		
		map.remove(userid);
		
		return getallactiveuser();
	}
	
	public List<User> getallactiveuser() {
		List<User> userlist= new ArrayList<User>();
		for (Map.Entry<String,User> entry : map.entrySet()) {
			if(entry.getValue().isActive()) {
				userlist.add(entry.getValue());
			}
		}
		return userlist;
	}
	public List<User> getalluser() {
		List<User> userlist= new ArrayList<User>();
		for (Map.Entry<String,User> entry : map.entrySet()) {
			
			userlist.add(entry.getValue());
			
		}
		return userlist;
	}
	public User getuserbyid(String userid) {
		
		return map.get(userid);
	}
	public boolean doesuserexist(String userid) {
		
		boolean existance= map.containsKey(userid);
		
		if(existance)
		{
			if(getuserbyid(userid).isActive())
			{
				return Boolean.TRUE;
			}
			
		}
		return Boolean.FALSE;
	}
	
	private String getid() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuilder idbuilder=new StringBuilder();
		idbuilder.append(calendar.get(Calendar.YEAR));
		idbuilder.append("-");
		idbuilder.append(calendar.get(Calendar.MONTH));
		idbuilder.append(calendar.get(Calendar.DATE));
		idbuilder.append("-");
		idbuilder.append(id++);
		return idbuilder.toString();
	}
}
