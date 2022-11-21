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
public class Review implements Serializable {
	private int reviewId;
	private String userId;
	private String userName;
	private String productId;
	private int orderId;
	private int lineNumber;
	private String reviewTitle;
	private String reviewContent;
	private Date reviewDate;
	private LineItem lineItem;
	
	public void initReview(String productId, String userId, int orderId, LineItem lineItem) {
		this.productId = productId;
		this.userId = userId;
		this.orderId = orderId;
		this.lineItem = lineItem;
		this.lineNumber = lineItem.getLineNumber();
		
		reviewTitle="";
		reviewContent="";
		reviewDate = new Date();
	}

	public void initReview(Review review) {
		this.reviewId = review.getReviewId();
		this.userId = review.getUserId();
		this.productId = review.getProductId();
		this.orderId = review.getOrderId();
		this.lineNumber = review.getLineNumber();
		this.reviewTitle = review.getReviewTitle();
		this.reviewContent = review.getReviewContent();
		this.reviewDate = review.getReviewDate();
	}
	
}
