package com.example.plantmall.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@SuppressWarnings("serial")
public class Category implements Serializable{
	private String categoryId;
	private String c_name;
}
