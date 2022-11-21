package com.example.plantmall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.plantmall.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AccountMgmtController {
	
	@Autowired
	private AuthService authService;
	

	@Value("/auth/accountMgmt")
	private String authView;
	
	@RequestMapping(method=RequestMethod.GET)
	private String showView(HttpServletRequest request, HttpSession session) throws Exception{
		return authView;
	}
	
	@RequestMapping(path="/delete")
	private String deleteView(HttpServletRequest request, HttpSession session) throws Exception{
		UserSession userSession=
				(UserSession)WebUtils.getSessionAttribute(request, "userSession");
		
		authService.deleteUser(userSession.getUser().getUserId());
		
		return authView;
	}	
	
	
	
	
}
