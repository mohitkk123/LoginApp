package com.cg.loginapp.dto;

public class UserCredential {

	
	String email,password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public UserCredential() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserCredential(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
