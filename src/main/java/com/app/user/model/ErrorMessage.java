package com.app.user.model;

public class ErrorMessage {

	private String code;
	private String field;
	private String message;
	
	public ErrorMessage() {
		super();
		this.code = "N.A";
		this.field = "N.A";
		this.message = "N.A";
	}
	public ErrorMessage(String code, String field, String message) {
		super();
		this.code = code;
		this.field = field;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
