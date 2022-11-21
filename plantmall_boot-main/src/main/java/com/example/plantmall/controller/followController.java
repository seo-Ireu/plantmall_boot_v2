package com.example.plantmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class followController {
	
		//팔로워확인
		@RequestMapping("/userPage/follower")
		public String showFollower() {
			return "followerList";
			}
		
		//팔로잉확인
		@RequestMapping("/userPage/following")
		public String showFollowing() {
			return "followingList";
			}

}