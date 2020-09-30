package com.cg.loginapp.service;


import java.util.ArrayList;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.cg.loginapp.dao.LoginDaoI;
import com.cg.loginapp.dto.PasswordData;
import com.cg.loginapp.dto.UserCredential;
import com.cg.loginapp.entity.UserData;
@Transactional
@Service
public class LoginService implements LoginServiceI{

	
	@Autowired
	LoginDaoI loginDao;
	
	
	//------------------------ Login Application --------------------------
	/*******************************************************************************************************
	* - Function Name : addUser - Input Parameters : UserData user Return Type :
	* int -  - Author : Mohit kumar Mastana -
	* Creation Date : 21/09/2020 - Description : to add user 
	* 
	*
	*******************************************************************************************************/
	
	
	@Override
	public int addUser(UserData user) {

		
		return loginDao.addUser(user);
	
	}
	

	//------------------------ Login Application --------------------------
	/*******************************************************************************************************
	* - Function Name : doLogin - Input Parameters : credential cred Return Type :
	* int -  - Author : Mohit kumar Mastana -
	* Creation Date : 21/09/2020 - Description : to do login action into database
	* 
	*
	*******************************************************************************************************/

	@Override
	public UserData doLogin(UserCredential credential) {

		return loginDao.doLogin(credential);
		
	}
	
	//------------------------ Login Application --------------------------
		/*******************************************************************************************************
		* - Function Name : getAllUser - Input Parameters :  Return Type :
		* ArrayList<USerdata> -  - Author : Mohit kumar Mastana -
		* Creation Date : 21/09/2020 - Description : to get all user from  database
		* 
		*
		*******************************************************************************************************/

	@Override
	public ArrayList<UserData> getAllUser() {

		
		
		return loginDao.getAllUser();
	}

	//------------------------ Login Application --------------------------
			/*******************************************************************************************************
			* - Function Name : getUser - Input Parameters :String email  Return Type :
			* USerdata -  - Author : Mohit kumar Mastana -
			* Creation Date : 21/09/2020 - Description : to get  user from  database
			* 
			*
			*******************************************************************************************************/
	
	@Override
	public UserData getUser(String email) {

		
		
		return loginDao.getUser(email);
	}
	
	//------------------------ Login Application --------------------------
	/*******************************************************************************************************
	* - Function Name : sendMail - Input Parameters :String email , int id  Return Type :
	* String -  - Author : Mohit kumar Mastana -
	* Creation Date : 21/09/2020 - Description : to send email to user
	* 
	*
	*******************************************************************************************************/

	@Override
	public String sendMail(String email,int id){

		return loginDao.sendMail(email, id);
	}
	
	//------------------------ Login Application --------------------------
		/*******************************************************************************************************
		* - Function Name : updatePassword - Input Parameters :Password data  Return Type :
		* String -  - Author : Mohit kumar Mastana -
		* Creation Date : 21/09/2020 - Description : to update password of user
		* 
		*
		*******************************************************************************************************/

	@Override
	public String updatePassword(PasswordData data) {

		
		
		return loginDao.updatePassword(data);
	}

}
