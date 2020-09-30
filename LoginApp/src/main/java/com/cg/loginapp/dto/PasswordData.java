package com.cg.loginapp.dto;

public class PasswordData {
	
	String token,password,confirmPassword;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public PasswordData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PasswordData(String token, String password, String confirmPassword) {
		super();
		this.token = token;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
