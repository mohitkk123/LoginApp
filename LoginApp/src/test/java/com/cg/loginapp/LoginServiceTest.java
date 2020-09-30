package com.cg.loginapp;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.cg.loginapp.dto.UserCredential;
import com.cg.loginapp.entity.UserData;
import com.cg.loginapp.service.LoginServiceI;

@SpringBootTest
public class LoginServiceTest {
	
	
	@Autowired
	LoginServiceI loginService;
	
	
	//------------------------ Login Application Test--------------------------
	/*******************************************************************************************************
	* - Function Name : doLoginTest
	* 
	* - Test checks login functionality with valid inputs
	* 
	* 
	*******************************************************************************************************/

	
	@Test
	public void doLoginTest() {
		UserCredential cred=new UserCredential("mkmastana.33@gmail.com","mohita");
		
		UserData user=loginService.doLogin(cred);
		
		Assertions.assertNotNull(user);
		
	}
	
	
	//------------------------ Login Application Test--------------------------
	/*******************************************************************************************************
	* - Function Name : doLoginTest2
	* 
	* - Test checks login functionality with invalid inputs
	* 
	* 
	*******************************************************************************************************/
	
	@Test
	public void doLoginTest2() {
		UserCredential cred=new UserCredential("mkm.33@gmail.com","mohita");
		
		UserData user=loginService.doLogin(cred);
		
		Assertions.assertNull(user);
		
	}
	
	
	//------------------------ Login Application Test--------------------------
	/*******************************************************************************************************
	* - Function Name : getAllUsersTest
	* 
	* - Test to get All Users
	* 
	* 
	*******************************************************************************************************/
	
	@Test
	public void getAllUserTest() {
		
		ArrayList<UserData> users=loginService.getAllUser();
		
		Assertions.assertNotNull(users);
		
	}
	
	
	//------------------------ Login Application Test--------------------------
	/*******************************************************************************************************
	* - Function Name : getUserTest
	* 
	* - Test to search email for forgot password functionality
	* 
	* 
	*******************************************************************************************************/
	
	@Test
	public void getUserTest() {
		
	UserData users=loginService.getUser("mkmastana.33@gmail.com");
		
		Assertions.assertNotNull(users);
		
	}
	
	//------------------------ Login Application Test--------------------------
	/*******************************************************************************************************
	* - Function Name : sendMailTest
	* 
	* - Test to check email functionality with valid input
	* 
	* 
	*******************************************************************************************************/
	
	@Test
	public void sendMailTest() {
		
		String result=loginService.sendMail("makmastana.33@gmail.com", 5);
		
		Assertions.assertEquals("200",result);
		
	}
	
	//------------------------ Login Application Test--------------------------
		/*******************************************************************************************************
		* - Function Name : sendMail2Test
		* 
		* - Test to check email functionality with invalid input
		* 
		* 
		*******************************************************************************************************/
		
	
	@Test
	public void sendMailTst2() {
		
		String result=loginService.sendMail("makmastana.33@gmail.com", 40);
		
		Assertions.assertEquals("400",result);
		
	}
	
	
	
	
	

}
