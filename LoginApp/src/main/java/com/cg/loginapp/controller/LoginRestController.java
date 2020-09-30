package com.cg.loginapp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.loginapp.dto.PasswordData;
import com.cg.loginapp.dto.UserCredential;
import com.cg.loginapp.entity.UserData;
import com.cg.loginapp.exception.LoginException;
import com.cg.loginapp.service.LoginServiceI;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class LoginRestController {

	//service initialization
	
	@Autowired 
	LoginServiceI loginService;
	
	@GetMapping("/getUsers")
	public ArrayList<UserData> getDashboard() {
		ArrayList<UserData> users=loginService.getAllUser();
		
		if(users.isEmpty()) {
			throw new LoginException("there are no users yet");
		}else {

			return users;
		}
	}
	
	
	
	
	// ------------------------ Login Application --------------------------
	/*******************************************************************************************************
	 * - Function Name : addUser - Input Parameters : UserData u Return Type :
	 * ResponseEntity<Integer> - Throws : UserDataException - Author : Mohit kumar Mastana -
	 * Creation Date : 21/09/2020 - Description : to add a user into the
	 * database
	 * 
	 * @throws UserException
	 *******************************************************************************************************/

@PostMapping("/addUser")
public ResponseEntity<Integer> addUser(@RequestBody UserData user){
		int userId=loginService.addUser(user);
		if(userId==0) {
			throw new LoginException("something went wrong try again");
		}else {
			return new ResponseEntity<Integer>(userId,HttpStatus.OK);

		}
		
}


// ------------------------ Login Application --------------------------
/*******************************************************************************************************
 * - Function Name : doLogin - Input Parameters : UserCrdntial c Return Type :
 * ResponseEntity<UserData> - Throws : UserDataException - Author : Mohit kumar Mastana -
 * Creation Date : 21/09/2020 - Description : to perform login into the
 * database
 * 
 * @throws UserException
 *******************************************************************************************************/

@PostMapping("/Login")
public ResponseEntity<UserData> doLogin(@RequestBody UserCredential credential){
	UserData user=loginService.doLogin(credential);
	if(user==null) {
		throw new LoginException("email or password is wrong...please try again later");
	}else {
		return new ResponseEntity<UserData>(user,HttpStatus.OK);

	}
	
}

//------------------------ Login Application --------------------------
/*******************************************************************************************************
* - Function Name : searchUserAndSendMail - Input Parameters : String email Return Type :
* ResponseEntity<String> - Throws : UserDataException - Author : Mohit kumar Mastana -
* Creation Date : 21/09/2020 - Description : to send email to user 
* 
* @throws UserException
*******************************************************************************************************/

/////////////////////////////////sent email also in this function

@GetMapping("/forgotPassword/{email}")
public  ResponseEntity<String> SearchUserAndSendMail(@PathVariable String email) {
	
	UserData user=loginService.getUser(email);
	
		if(user==null) {
			throw new LoginException("email is not registered...try another");
			
			
		}else {
			String checkMessage=loginService.sendMail(email,user.getId());
			switch(checkMessage) {
			
			case "200":
				return new ResponseEntity<String>(user.getEmail(),HttpStatus.OK);
				
				
			case "400":
				throw new LoginException("cannot reset token");
			case "401":
				throw new LoginException("address not found");
			case "402":
				throw new LoginException("Please connect to the internet");
			case "403":
				throw new LoginException("Authentication error...try valid credentials");
			case "404":
				throw new LoginException("something went wrong...try another later 1");	
				
			default:
				
				throw new LoginException("something went wrong...try another later 2");
				
				
			}
			
			
			
		}
	
}


//------------------------ Login Application --------------------------
/*******************************************************************************************************
* - Function Name : updatePassword - Input Parameters : PassowrdData c Return Type :
* ResponseEntity<String> - Throws : LoginDataException - Author : Mohit kumar Mastana -
* Creation Date : 21/09/2020 - Description : to perform update into the
* database
* 
* @throws UserException
*******************************************************************************************************/

@PostMapping("/updatePassword")
public ResponseEntity<String> updatePassword(@RequestBody PasswordData data){
	String status=loginService.updatePassword(data);
	switch(status) {
	case "200":
		return new ResponseEntity<String>("password updated successfully",HttpStatus.OK);
	
	case "401":
		throw new LoginException("wrong token value...pls try again");
		
	case "402":
		throw new LoginException("passwords do not match...enter correct password");
		
	default:
		throw new LoginException("something went wron try again");
		
	}
	
	
}








	
}
