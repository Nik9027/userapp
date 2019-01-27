package com.app.user.exceptionHandler;

import java.util.List;

import com.app.user.model.ErrorMessage;

public class BusinessException {

	private String resMsg;
	private String userId;
	private List<ErrorMessage> valErrors;
	
	
	public BusinessException(String resMsg, List<ErrorMessage> valErrors, String userId) {
		super();
		this.resMsg = resMsg;
		this.userId = userId;
		this.valErrors = valErrors;
		
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
