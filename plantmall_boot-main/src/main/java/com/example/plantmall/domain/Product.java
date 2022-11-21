package com.example.plantmall.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter @Setter @ToString
public class Product implements Serializable {
	private String productId;
	private String categoryId;
	private String userId;
	private String p_name;
	private String description;
	private int price;
	private String productImg;
	//제품에 리뷰 정보 필요
	private List<Review> reviews;
	private List<Enquiry> enquiryList;
}
