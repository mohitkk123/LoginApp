package com.cg.loginapp.service;

import java.util.ArrayList;

import com.cg.loginapp.dto.PasswordData;
import com.cg.loginapp.dto.UserCredential;
import com.cg.loginapp.entity.UserData;

import antlr.collections.List;

public interface LoginServiceI {
	
	public ArrayList<UserData> getAllUser();
	
	public int addUser(UserData user);
	
	public UserData doLogin(UserCredential credential);

	public UserData getUser(String email);
	
	public String sendMail(String email,int id);

	public String updatePassword(PasswordData data);

}
