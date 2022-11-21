package com.example.plantmall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class SignOffController {
	@RequestMapping("/signoff")
	public String handleRequest(HttpSession session) throws Exception {
		session.removeAttribute("userSession");
		session.removeAttribute("sessionCart");
		session.removeAttribute("enqForm");
		session.removeAttribute("reviewForm");
		session.removeAttribute("orderForm");
		System.out.println(session.getAttribute("sessionCart"));
		session.invalidate();
		return "redirect:/";
	}
}
