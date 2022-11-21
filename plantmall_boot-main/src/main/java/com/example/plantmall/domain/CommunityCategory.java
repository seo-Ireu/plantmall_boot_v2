package com.example.plantmall.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@SuppressWarnings("serial")
@Getter @Setter @ToString
public class CommunityCategory implements Serializable{

	private String ccId;
	private String ccName;
	private Boolean isForSeller;
	
}
