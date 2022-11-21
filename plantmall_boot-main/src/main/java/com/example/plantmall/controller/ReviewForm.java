package com.example.plantmall.controller;

import java.io.Serializable;

import javax.validation.Valid;

import com.example.plantmall.domain.Review;

@SuppressWarnings("serial")
public class ReviewForm implements Serializable{
	@Valid
	private final Review review = new Review();
	public Review getReview() {
		return review;
	}
}
