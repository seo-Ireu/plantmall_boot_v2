package com.example.plantmall.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.plantmall.domain.Profile;

public interface ProfileService {
	
	Profile getProfileByUserId(String userId);
	void createProfile(Profile profile);
	void updateProfile(Profile profile);

}
