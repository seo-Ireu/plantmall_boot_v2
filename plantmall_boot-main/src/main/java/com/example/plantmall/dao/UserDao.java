package com.example.plantmall.dao;

import com.example.plantmall.domain.User;

public interface UserDao {

	User getUser(String email);
	User getUser(String email, String password);
	void insertUser(User user);
	void updateUser(User user);

	void deleteUser(String id);
	User getUserById(String id);

}

