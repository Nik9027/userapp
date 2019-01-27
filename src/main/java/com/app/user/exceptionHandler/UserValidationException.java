package com.app.user.exceptionHandler;

import java.util.List;
import com.app.user.model.ErrorMessage;

public class UserValidationException extends RuntimeException {

	private String resMsg;
	private String userid="N.A";
	private List<ErrorMessage> valErrors;
	
	public UserValidationException(String resMsg, String userid, List<ErrorMessage> valErrors) {
		super();
		this.resMsg = resMsg;
		this.userid = userid;
		this.valErrors = valErrors;
	}

	public UserValidationException(String resMsg, List<ErrorMessage> valErrors) {
		super();
		this.resMsg = resMsg;
		this.valErrors = valErrors;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public List<ErrorMessage> getValErrors() {
		return valErrors;
	}

	public void setValErrors(List<ErrorMessage> valErrors) {
		this.valErrors = valErrors;
	}
	
	
}
