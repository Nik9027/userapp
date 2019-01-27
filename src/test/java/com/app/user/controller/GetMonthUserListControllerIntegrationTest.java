package com.app.user.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class GetMonthUserListControllerIntegrationTest {

	Logger logger= LoggerFactory.getLogger(GetMonthUserListControllerIntegrationTest.class);
	
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
		
		List<User> userlist= new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1990);
		SimpleDateFormat sdf= new SimpleDateFormat(ConstantsValues.userdateformat);
		String date1=sdf.format(cal.getTime());
		
		User user1=new User("2019-026-1001", "John", "Doe", "john@gmail.com", "123123", date1);
		User user2=new User("2019-026-1002", "nik", "sha", "nik@gmail.com", "889988", date1);
		
		userlist.add(user1);
		userlist.add(user2);
		
		cal.add(Calendar.MONTH, 1);
		User user3=new User("2019-026-1003", "ABC", "XYZ", "abc@gmail.com", "889900", "22-Dec-1990");
		userlist.add(user3);
		Mockito.when(repository.getallactiveuser()).thenReturn(userlist);
		this.mockmvc.perform(get("/app/user")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(userlist.size()-1))).andDo(print());
		
		Mockito.verify(repository).getallactiveuser();
	}
}
