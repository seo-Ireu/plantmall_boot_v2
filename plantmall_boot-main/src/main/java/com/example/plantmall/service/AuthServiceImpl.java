package com.example.plantmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.plantmall.dao.UserDao;
import com.example.plantmall.domain.User;
@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public User getUser(String email, String password) {
		return userDao.getUser(email, password);
	}

	@Override
	public void insertUser(User user) {
		userDao.insertUser(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public User getUser(String email) {
		return userDao.getUser(email);
	}

	@Override
	public void deleteUser(String id) {
		userDao.deleteUser(id);
		
	}
	
	@Override
	public User getUserById(String id) {
		return userDao.getUserById(id);
	}
}
