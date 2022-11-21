package com.example.plantmall.controller.Product;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter @Setter
public class ProductForm implements Serializable {
	private String productId;
	private String categoryId;
	private String p_name;
	private String description;
	private int price;
	private String productImg;
}
