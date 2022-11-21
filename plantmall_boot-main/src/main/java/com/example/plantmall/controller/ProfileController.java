package com.example.plantmall.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.example.plantmall.domain.Profile;
import com.example.plantmall.domain.User;
import com.example.plantmall.service.AuthService;
import com.example.plantmall.service.ProfileService;

@Controller
@RequestMapping("/myProfile")
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	// 마이페이지 프로필
	@GetMapping
	public String getMyProfile(Model model, @ModelAttribute("profileForm") ProfileForm profileForm, HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		User user = userSession.getUser();
		Profile profile = profileService.getProfileByUserId(user.getUserId());
		// 마이페이지 최초 접속일 경우 프로필 초기 설정
		if (profile == null) {
			System.out.println("create new profile page");
			model.addAttribute("newProfile", true);
			//model.addAttribute("profileForm", profileForm.getProfile());
			profileForm.getProfile();
			return "profile/ProfileForm";
		}
		// 존재하는 마이페이지 로드
		else {
			System.out.println("my profile page");
			model.addAttribute("user", user);
			model.addAttribute("profile", profile);
			return "profile/Profile";
		}

	}

	// 마이페이지 프로필 수정 페이지
	@GetMapping("/update")
	public ModelAndView updateMyProfile(ModelAndView mav, @ModelAttribute("profileForm") ProfileForm profileForm,
			HttpSession session) throws ModelAndViewDefiningException {
		System.out.println("update profile page");
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		User user = userSession.getUser();
		mav.addObject("newProfile", false);
		Profile profile = profileService.getProfileByUserId(user.getUserId());
		profileForm.setProfile(profile);
		mav.setViewName("profile/ProfileForm");
		
		return mav;
	}

	@PostMapping({"","/update"})
	public String submit(@ModelAttribute("profileForm") ProfileForm profileForm,
			@RequestParam("fileUrl") MultipartFile multipartFile, HttpServletRequest request, HttpSession session, ModelMap modelMap) {
		String requestUrl = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		System.out.println("requestUrl=" + requestUrl);
		
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		Profile profile = profileForm.getProfile();
		profile.setUserId(userSession.getUser().getUserId());
		
		//String uploadPath = request.getSession().getServletContext().getRealPath("/").concat("resources\\static\\images\\profileImages");
		//String uploadPath = request.getRealPath("/");
		String fileName = null; // 기본 경로와 별개로 작성되는 경로 + 파일이름

		if (multipartFile.getOriginalFilename() != null && !multipartFile.getOriginalFilename().equals("")) {
			// 파일 인풋박스에 첨부된 파일이 없다면(=첨부된 파일이 이름이 없다면)
			UUID uid = UUID.randomUUID();//같은 이미지들도 저장 가능하도록 uuid부여
			fileName = uid + "_" + multipartFile.getOriginalFilename();

			// gdsImg에 원본 파일 경로 + 파일명 저장
			profile.setProfileImg(fileName);
		}
		System.out.println("request={} " + request);
		System.out.println("profileInfo={} " + profileForm.getProfile().toString());
		System.out.println("multipartFile={} " + multipartFile);
		System.out.println("fileName : " + fileName);
		//프로필 생성
		if(requestUrl.equals("/myProfile"))
		{
			profileService.createProfile(profile);
			Profile createdProfile = profileService.getProfileByUserId(userSession.getUser().getUserId());
			if(createdProfile == null)
			{
				System.out.println("profile이 null임");
			}
		}
		//프로필 수정
		else
		{
			profileService.updateProfile(profile);
		}
		return "redirect:/myProfile";
	}
	
	@RequestMapping(value = "/getImage/{imageId}")
	@ResponseBody
	public byte[] getImage(@PathVariable String imageId, HttpServletRequest request)  {
	    String rpath = request.getSession().getServletContext().getRealPath("/");
	    rpath = rpath + "/" + imageId;
	    Path path = Paths.get(rpath);
	    byte[] data = null;
		try {
			data = Files.readAllBytes(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    return data;
	}

}