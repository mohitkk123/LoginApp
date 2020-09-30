package com.cg.loginapp.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;


import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.mail.MailException;
import org.springframework.stereotype.Repository;

import com.cg.loginapp.dto.PasswordData;
import com.cg.loginapp.dto.UserCredential;
import com.cg.loginapp.entity.UserData;


@Repository("userRepository")
public class LoginDao implements LoginDaoI{

	@PersistenceContext
	EntityManager em;
	
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
	
		
		if(user !=null) {
			em.persist(user);
			
			return user.getId();
			
		}else {
		return 0;
		}
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
	

		String qStr="SELECT user FROM UserData user where user.email like '%"+(credential.getEmail())+"%' AND user.password like '%"+(credential.getPassword())+"%'";
		TypedQuery<UserData> query=em.createQuery(qStr,UserData.class);
	
		try {
		return query.getSingleResult();
		
		}catch(NoResultException e) {
			return null;
		}
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
		
		String qStr="SELECT user FROM UserData user";
		TypedQuery<UserData> query=em.createQuery(qStr,UserData.class);
		
		try {
		return (ArrayList<UserData>) query.getResultList();
		
		}catch(NoResultException e) {
			return null;
		}
		
		
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
		
		String qStr="SELECT user FROM UserData user where user.email='"+(email)+"'";
		TypedQuery<UserData> query=em.createQuery(qStr,UserData.class);
		
		try {
		
			
			
			UserData user=query.getSingleResult();
			sendMail(email,user.getId());
			
		return user; 
		
		}catch(NoResultException e) {
			
			
			return null;
		}
		
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
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
	public String sendMail(String email,int id)  {
	
		
		 Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   try {
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("mohitsean2798@gmail.com", "Mohit@27");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("mohitsean2798@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		   msg.setSubject("password reset code");
		   int resetCode = new Random().nextInt(900000) + 100000;
		   
		   msg.setContent(""+resetCode, "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Use this code", "text/html");
		   
		  

		   

		 
		   Transport.send(msg);
		   
			boolean flag=   updateToken(id,resetCode);
			
			if(flag) {
				return "200";
			}else {
				return "400";
			}
		   
		   
		   }catch(AddressException e) {
				
				return "401";
			}catch(MailException e) {
				
				return "402";
			}
		   
			catch(MessagingException e) {
				
				return "403";
			}
		   
		   catch(Exception e) {
				
				return "404";
			}
		   
		 
		   
	
		
		
		
		
		
		
		
		
		
	}
	
	//------------------------ Login Application --------------------------
	/*******************************************************************************************************
	* - Function Name : updatePassword - Input Parameters :Password data  Return Type :
	* String -  - Author : Mohit kumar Mastana -
	* Creation Date : 21/09/2020 - Description : to update password of user
	* 
	*
	*******************************************************************************************************/
	
	
	public boolean updateToken(int id,int resetToken) {
		
		UserData user=em.find(UserData.class, id);
		
		if(user!=null) {
			user.setResetToken(resetToken+"");

			em.merge(user);
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public String updatePassword(PasswordData data) {

		String qStr="SELECT user FROM UserData user where user.resetToken='"+(data.getToken())+"'";
		TypedQuery<UserData> query=em.createQuery(qStr,UserData.class);
		
		
		try {
		UserData user=query.getSingleResult();
		if(data.getPassword().equals(data.getConfirmPassword())) {

			user.setPassword(data.getPassword());

			user.setResetToken(null);
			em.merge(user);
			
			return "200";
		}else {
			return "402";
		}
				
		}catch(NoResultException e) {
			return "401";
		}
		
	}
	
	

}
