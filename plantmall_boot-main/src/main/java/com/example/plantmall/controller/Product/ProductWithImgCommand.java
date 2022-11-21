package com.example.plantmall.controller.Product;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.plantmall.domain.Enquiry;
import com.example.plantmall.domain.Review;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter @Setter
@ToString
public class ProductWithImgCommand implements Serializable {
	private String productId;
	private String categoryId;
	private String userId;
	private String p_name;
	private String description;
	private int price;
	private MultipartFile productImgValue;
}
