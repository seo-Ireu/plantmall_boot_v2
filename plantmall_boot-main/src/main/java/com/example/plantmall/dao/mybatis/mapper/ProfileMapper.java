package com.example.plantmall.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.example.plantmall.domain.Profile;

@Mapper
public interface ProfileMapper {
	Profile getProfileByUserId(String userId);
	void createProfile(Profile profile);
	void updateProfile(Profile profile);
	

}
