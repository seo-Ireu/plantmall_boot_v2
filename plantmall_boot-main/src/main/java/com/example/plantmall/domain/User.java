package com.example.plantmall.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class User {
	@NotNull
	private String userId;
	@NotNull
	private String userName;
	
	@NotNull
	@Pattern(regexp="^(?=.*[A-Z])(?=.*[~!@#$%^&*])[a-zA-z\\d]{8,}")
	private String password;
	
	@NotNull
	@Pattern(regexp = "^[\\w]+@[\\w]+\\.[\\w]*\\.[\\w]{2,3}")
	private String email;
	
	@Pattern(regexp="^01[01679]-\\d{3,4}-\\d{4}")
	private String phone;
	
	@Pattern(regexp = "\\d{5}")
	private String zipcode;
	private String address;
	
	private List<Review> reviewList = new ArrayList<Review>();
	private List<Enquiry> enquiryList = new ArrayList<Enquiry>();
	private List<EnqComm> enquiryCommList = new ArrayList<EnqComm>();
}