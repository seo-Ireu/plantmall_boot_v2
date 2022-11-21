package com.example.plantmall.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.plantmall.dao.ProfileDao;
import com.example.plantmall.dao.mybatis.mapper.ProfileMapper;
import com.example.plantmall.domain.Profile;

@Repository
public class MybatisProfileDao implements ProfileDao{

	@Autowired
	private ProfileMapper profileMapper;
	@Override
	public Profile getProfileByUserId(String userId) throws DataAccessException {
		// TODO Auto-generated method stub
		return profileMapper.getProfileByUserId(userId);
	}
	
	@Override
	public void createProfile(Profile profile) throws DataAccessException {
		// TODO Auto-generated method stub
		profileMapper.createProfile(profile);
	}

	@Override
	public void updateProfile(Profile profile) throws DataAccessException {
		// TODO Auto-generated method stub
		profileMapper.updateProfile(profile);
	}

	

}
