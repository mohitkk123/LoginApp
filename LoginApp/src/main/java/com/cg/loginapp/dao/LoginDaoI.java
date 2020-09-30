package com.cg.loginapp.dao;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Repository;

import com.cg.loginapp.dto.PasswordData;
import com.cg.loginapp.dto.UserCredential;
import com.cg.loginapp.entity.UserData;


@Repository("userRepository")
public interface LoginDaoI {
	
	
	public int addUser(UserData user);
	
	public UserData doLogin(UserCredential credential);

	public ArrayList<UserData> getAllUser();

	public UserData getUser(String email);
	
	public String sendMail(String email,int id);

	public String updatePassword(PasswordData data);

}
