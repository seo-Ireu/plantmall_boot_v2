package com.example.plantmall.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import com.example.plantmall.domain.Profile;

public interface ProfileDao {
	
	Profile getProfileByUserId(String userId)throws DataAccessException;
	void createProfile(Profile profile)throws DataAccessException;
	void updateProfile(Profile profile)throws DataAccessException;

}
