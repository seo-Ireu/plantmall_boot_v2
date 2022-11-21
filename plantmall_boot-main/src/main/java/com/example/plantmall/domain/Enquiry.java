package com.example.plantmall.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Enquiry implements Serializable {
	private int enquiryId;
	private String userId;
	private String userName;
	private String productId;
	private String enqContent;
	private Date enquiryDate;
	private EnqComm enqComm;
	
	public void initEnq(String productId, String userId) {
		this.productId = productId;
		this.userId = userId;
		
		enqContent="";
		enquiryDate = new Date();
		enqComm = null;
	}

	public void initEnq(Enquiry enq) {
		this.enquiryId = enq.getEnquiryId();
		this.userId = enq.getUserId();
		this.productId = enq.getProductId();
		this.enqContent = enq.getEnqContent();
		this.enquiryDate = enq.getEnquiryDate();
		this.enqComm = enq.getEnqComm();
	}

	
}
