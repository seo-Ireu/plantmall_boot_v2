package com.example.plantmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	@Autowired
//	private UserService userService;
	
	//회원페이지 접속
	@RequestMapping("/userPage")
	public String story() {
		return "UserPage";
	}
	
	//프로필수정페이지 접속
	@RequestMapping("/userPage/profile")
	public String updateProfile() {
		return "UserProfile";
	}
	
	
	//피드추가버튼 클릭
	@RequestMapping("/userPage/upload")
	public String feedUpload() {
		return "FeedUpload";
	}
	
	//피드 클릭
	@RequestMapping("/userPage/feed")
	public String showFeed() {
		return "Feed";
	}
	
}