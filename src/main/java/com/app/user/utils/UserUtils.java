package com.app.user.utils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.app.user.model.ConstantsValues;

@Component
public class UserUtils {

	public Boolean isDateValid(String birthdate) {
		 System.out.println(birthdate);
		 DateFormat dateFormat= new SimpleDateFormat(ConstantsValues.userdateformat);

		    try{
		    	Calendar cal = Calendar.getInstance();
		    	Date formattedDate = dateFormat.parse(birthdate.toString());
		    	String currentdatestring=dateFormat.format(cal.getTime());
		    	Date currentDate=dateFormat.parse(currentdatestring);
		    	
		        if(birthdate.length()>11 || formattedDate.after(currentDate)) {
		        	return Boolean.FALSE;
		        }
		    	
		        System.out.println(formattedDate);
		        
		        return Boolean.TRUE;
		    }catch(ParseException parseEx){
		        return Boolean.FALSE;
		    }
		
	}
	
	public Boolean isPinCodeValid(String pincode)
	{
		try {
			
		Integer pin= Integer.parseInt(pincode);
		return Boolean.TRUE;
		}
		catch (Exception e) {
			return Boolean.FALSE;
		}
	}
}
