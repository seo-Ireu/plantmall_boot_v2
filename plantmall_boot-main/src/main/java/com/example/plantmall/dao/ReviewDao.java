package com.example.plantmall.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.plantmall.domain.Review;

public interface ReviewDao {
	List<Review> getReviewsByProductId(String productId) throws DataAccessException;
	Review getReview(Review review) throws DataAccessException;
	Review getReviewByReviewId(int reviewId) throws DataAccessException;
	void insertReview(Review review) throws DataAccessException;
	Review updateReview(Review review) throws DataAccessException;
}