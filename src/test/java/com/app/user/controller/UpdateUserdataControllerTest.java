package com.app.user.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.user.exceptionHandler.UserValidationException;
import com.app.user.model.User;
import com.app.user.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateUserdataControllerTest {

	@Mock
	UserService service;
	
	
	@InjectMocks
	UpdateUserController updateUserController;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test(expected=UserValidationException.class)
	public void checkForValidationException() {
		updateUserController.updateuserdata("2019-026-1001", null, null);
	}
	
	@Test
	public void updateuserdata() {
		User user=new User("2019-026-1001","ABC","XYZ","nik@ymail.com","123109","26-Mar-1990");
		Mockito.when(service.updateuser(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(user);
		@SuppressWarnings("unchecked")
		ResponseEntity<User> response= (ResponseEntity<User>) updateUserController.updateuserdata(user.getUserID(), user.getBirthdate(), user.getPincode());
		assertEquals(response.getBody().getPincode(), user.getPincode());
		assertEquals(response.getBody().getBirthdate(), user.getBirthdate());
		
	}
	
	
	
}
