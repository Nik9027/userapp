package com.app.user.exceptionHandler;

import java.util.List;

import com.app.user.model.ErrorMessage;

public class UserEmailAlreadyExistException extends RuntimeException {

	private String resMsg;
	private String email;
	private List<ErrorMessage> valErrors;
	
	public UserEmailAlreadyExistException(String resMsg, List<ErrorMessage> valErrors) {
		super();
		this.resMsg = resMsg;
		this.valErrors = valErrors;
	}

	public UserEmailAlreadyExistException(String resMsg, String email, List<ErrorMessage> valErrors) {
		super();
		this.resMsg = resMsg;
		this.email = email;
		this.valErrors = valErrors;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ErrorMessage> getValErrors() {
		return valErrors;
	}

	public void setValErrors(List<ErrorMessage> valErrors) {
		this.valErrors = valErrors;
	}
	
	 
	
}
