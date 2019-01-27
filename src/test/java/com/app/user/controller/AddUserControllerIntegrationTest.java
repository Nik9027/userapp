package com.app.user.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.spi.LoggerContext;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.app.user.model.ConstantsValues;
import com.app.user.model.User;
import com.app.user.repository.UserRepository;
import com.fasterxml.jackson.annotation.JacksonInject.Value;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AddUserControllerIntegrationTest {

	Logger logger= LoggerFactory.getLogger(AddUserControllerIntegrationTest.class);
	
	/*
	 * @InjectMocks GetMonthUserListController getMonthUserListController;
	 */
	
	@MockBean
	UserRepository repository;
	
	@Autowired
	WebApplicationContext wac;
	
	
	MockMvc mockmvc;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		this.mockmvc= MockMvcBuilders.webAppContextSetup(wac).build();
	}
	@Test
	public void testcurrentMonthUserList() throws Exception {
		
		
		User user=new User("2019-026-1001", "John", "Doe", "john@gmail.com", "123123", "10-Mar-1992");
		
		Mockito.when(repository.adduser(ArgumentMatchers.any(User.class))).thenReturn(user);
		
		this.mockmvc.perform(post("/app/user")
				.param(ConstantsValues.FNAME, user.getFname())
				.param(ConstantsValues.LNAME, user.getLname())
				.param(ConstantsValues.EMAIL, user.getEmail())
				.param(ConstantsValues.PINCODE, user.getPincode())
				.param(ConstantsValues.BIRTHDATE, user.getBirthdate()))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.fname").value(user.getFname()))
		.andDo(print());
		
		Mockito.verify(repository).adduser(ArgumentMatchers.any(User.class));
	}
}
