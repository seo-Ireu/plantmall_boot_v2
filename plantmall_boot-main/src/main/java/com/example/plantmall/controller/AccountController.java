package com.example.plantmall.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.plantmall.dao.UserDao;
import com.example.plantmall.dao.mybatis.MybatisUserDao;
import com.example.plantmall.dao.mybatis.mapper.UserMapper;
import com.example.plantmall.domain.User;
import com.example.plantmall.service.AuthService;

@Controller
@RequestMapping({"/signup","/auth/update"})
public class AccountController{
	
	@Value("auth/signupForm")
	private String formViewName;
	
	@Value("auth/created")
	private String successViewName;
	
	@Autowired
	private AuthService authService;
	
	@ModelAttribute("userForm")
	public UserForm formBackingObject(HttpServletRequest request) throws Exception{
		UserSession userSession=
				(UserSession)WebUtils.getSessionAttribute(request, "userSession");
		
		if(userSession!=null) {
			return new UserForm(
					authService.getUser(userSession.getUser().getEmail()));
		}else {
			return new UserForm();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm(HttpServletRequest request) throws Exception{
		UserSession userSession=
				(UserSession)WebUtils.getSessionAttribute(request, "userSession");
		
		ModelAndView mav  = new ModelAndView();
		mav.setViewName(formViewName);
		
		if(userSession!=null) {
			mav.addObject("isNew",false);
		}else {
			mav.addObject("isNew",true);
		}
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(HttpServletRequest request, HttpSession session, @ModelAttribute("userForm") UserForm userForm) throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName(successViewName);
			if(userForm.isNewUser()) {
				//회원가입 진행
				authService.insertUser(userForm.getUser());
				mav.addObject("isNewUser", true);
				
			}else {
				//회원수정 진행
				authService.updateUser(userForm.getUser());
				mav.addObject("isNewUser", false);
			}
			
			UserSession userSession = new UserSession(
					authService.getUser(userForm.getUser().getEmail()));
			
			session.setAttribute("userSession", userSession);
			return mav;
	}

	
	@RequestMapping(path = "/auth/created", method=RequestMethod.GET)
	public String created(Model model, HttpServletRequest request, HttpSession session) {
		return "auth/created";
	}	
	
	
}
