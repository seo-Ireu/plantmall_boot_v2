package com.example.plantmall.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.plantmall.dao.UserDao;
import com.example.plantmall.dao.mybatis.mapper.UserMapper;
import com.example.plantmall.domain.User;
@Repository
public class MybatisUserDao implements UserDao{

	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUser(String email) {
		return userMapper.getUserByEmail(email);
	}
	@Override
	public User getUser(String email, String password) {
		return userMapper.getUserByEmailAndPassword(email, password);
	}

	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}
	
	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}
	@Override
	public void deleteUser(String id) {
		userMapper.deleteUser(id);
		
	}
	@Override
	public User getUserById(String id) {
		return userMapper.getUserById(id);
	}


}
