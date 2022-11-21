package com.example.plantmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.plantmall.dao.ReviewDao;
import com.example.plantmall.domain.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	
	@Override
	public List<Review> getReviewsByProductId(String productId) {
		// TODO Auto-generated method stub
		return reviewDao.getReviewsByProductId(productId);
	}

	@Override
	public Review getReview(Review review) {
		// TODO Auto-generated method stub
		return reviewDao.getReview(review);
	}
	
	@Override
	public Review getReviewByReviewId(int reviewId) {
		// TODO Auto-generated method stub
		return reviewDao.getReviewByReviewId(reviewId);
	}
	
	@Override
	public void insertReview(Review review) throws DataAccessException {
		// TODO Auto-generated method stub
		reviewDao.insertReview(review);
	}

	@Override
	public Review updateReview(Review review) {
		// TODO Auto-generated method stub
		return reviewDao.updateReview(review);
	}

}
