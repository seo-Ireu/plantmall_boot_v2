package com.example.plantmall.controller;

import java.io.Serializable;

import com.example.plantmall.domain.User;

@SuppressWarnings("serial")
public class UserForm implements Serializable{
	
	private User user;
	private boolean newUser;
	private String repeatedPassword;
	
	public UserForm(User user) {
		this.user = user;
		this.newUser =false;
	}
	
	public UserForm() {
		this.user = new User();
		this.newUser = true;
	}
	
	public User getUser() {
		return user;
	}

	public boolean isNewUser() {
		return newUser;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}
	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}
	
	

}
