package com.app.user.model;


public class User {

	private String id;
	private String fname;
	private String lname;
	private String email;
	private String pincode;
	private String birthdate;
	private boolean active= Boolean.TRUE;
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getUserID() {
		return id;
	}
	public void setUserID(String userID) {
		this.id = userID;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getBirthdate() {	
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public User(String userID, String fname, String lname, String email, String pincode, String birthdate) {
		super();
		this.id = userID;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.pincode = pincode;
		this.birthdate = birthdate;
	}
	
	public User( String fname, String lname, String email, String pincode, String birthdate) {
		super();
		this.id = "N.A";
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.pincode = pincode;
		this.birthdate = birthdate;
	}
	@Override
	public String toString() {
		
		return "[userid="+this.id+"]";
	}
	
}
