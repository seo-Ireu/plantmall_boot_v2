package com.example.plantmall.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.plantmall.dao.ProfileDao;
import com.example.plantmall.domain.Profile;

@Service
public class ProfileServiceImpl implements ProfileService{

	@Autowired
	private ProfileDao profileDao;
	@Override
	public Profile getProfileByUserId(String userId) {
		// TODO Auto-generated method stub
		return profileDao.getProfileByUserId(userId);
		
		
	}
	
	@Override
	public void createProfile(Profile profile) {
		// TODO Auto-generated method stub
		profileDao.createProfile(profile);
	}

	@Override
	public void updateProfile(Profile profile) {
		// TODO Auto-generated method stub
		profileDao.updateProfile(profile);
		
	}

	

}
