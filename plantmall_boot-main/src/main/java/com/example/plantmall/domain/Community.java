package com.example.plantmall.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter @Setter @ToString
public class Community implements Serializable{
	
	private String communityId;
	private String cComment;
	private String fundingId;
	private String userId;
	private String title;
	private Date create_at;
	private CommunityCategory communityCategory;
	
}
