package com.example.plantmall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.plantmall.domain.User;
import com.example.plantmall.service.AuthService;

@Controller
@SessionAttributes("userSession")
@RequestMapping("/login")
public class SignonController {
	@Value("auth/loginForm")
	private String formViewName;
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm() {
		ModelAndView mav  = new ModelAndView();
		mav.setViewName(formViewName);

		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView handleRequest(HttpServletRequest request,HttpSession session,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam(value="forwardAction", required=false) String forwardAction,
			Model model) throws Exception{
		User user = authService.getUser(email, password);
		if(user ==null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/auth/error");
			mav.addObject("errorMessage", "아이디, 비밀번호를 확인해주세요");
			return mav;
		}else {
			UserSession userSession = new UserSession(user);
			model.addAttribute("userSession",userSession);

			if (forwardAction != null) 
				return new ModelAndView("redirect:" + forwardAction);
			else 
				return new ModelAndView("redirect:/");
		}
	}
	
	
}
