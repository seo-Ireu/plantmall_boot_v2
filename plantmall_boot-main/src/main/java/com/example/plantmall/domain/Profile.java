package com.example.plantmall.domain;



import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Profile implements Serializable{
	
	private int profileId;
	private String profileImg;
	private int FeedCnt;
	private int followerCnt;
	private int followingCnt;
	private String nickName;
	private String introduce;
	private String userId;

}


