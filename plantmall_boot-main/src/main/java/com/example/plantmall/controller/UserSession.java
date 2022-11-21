package com.example.plantmall.controller;

import java.io.Serializable;

import com.example.plantmall.domain.User;

@SuppressWarnings("serial")
public class UserSession  implements Serializable{
	
	private User user;
	
	public UserSession(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}


}
