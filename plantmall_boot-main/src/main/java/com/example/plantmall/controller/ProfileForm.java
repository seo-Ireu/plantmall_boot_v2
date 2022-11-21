package com.example.plantmall.controller;

import com.example.plantmall.domain.Profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ProfileForm {
	
	private Profile profile = new Profile();
	public Profile getProfile()
	{
		return profile;
	}
	public void setProfile(Profile profile)
	{
		this.profile = profile;
	}

	
	
	

}
