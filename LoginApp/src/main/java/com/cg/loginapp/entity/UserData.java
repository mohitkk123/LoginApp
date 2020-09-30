package com.cg.loginapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private int id;

	@Column(name = "email", nullable = false, unique = true)
	
	
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "first_name")

	private String firstName;

	@Column(name = "last_name")
	
	private String lastName;



	@Column(name = "reset_token")
	private String resetToken;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}



	public UserData() {
		super();

	}



	public UserData(String email, String password, String firstName, String lastName, String resetToken) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.resetToken = resetToken;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getResetToken() {
		return resetToken;
	}



	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
	
	
	
}
