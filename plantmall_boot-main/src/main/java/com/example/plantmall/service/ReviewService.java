package com.example.plantmall.service;

import java.util.List;

import com.example.plantmall.domain.Review;

public interface ReviewService {
	List<Review> getReviewsByProductId(String productId);
	Review getReview(Review review);
	Review getReviewByReviewId(int reviewId);
	void insertReview(Review review);
	Review updateReview(Review review);
}
