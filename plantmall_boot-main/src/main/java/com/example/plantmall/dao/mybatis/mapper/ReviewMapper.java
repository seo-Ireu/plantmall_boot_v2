package com.example.plantmall.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.plantmall.domain.Review;

@Mapper
public interface ReviewMapper {
	List<Review> getReviewsByProductId(String productId);
	Review getReview(Review review);
	Review getReviewByReviewId(int reviewId);
	void insertReview(Review review);
	void updateReview(Review review);
}
