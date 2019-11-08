package com.data;

// Below is similar JSON object for payload
public class Users {
	String grant_type_type;
	String password;
	String username;
	
	public Users()
	{
		
	}
	
	public Users(String username, String password,String grant_type_type)
	{
	this.username = username;
	this.password = password;
	this.grant_type_type= grant_type_type;
	}

	public String getgrant_type() {
		return grant_type_type;
	}

	public void setgrant_type_type(String grant_type_type) {
		this.grant_type_type = grant_type_type;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}
	
	// getter and setter 

}
