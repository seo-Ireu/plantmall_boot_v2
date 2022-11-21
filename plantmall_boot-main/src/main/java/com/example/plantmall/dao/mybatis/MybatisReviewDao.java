package com.example.plantmall.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.plantmall.dao.ReviewDao;
import com.example.plantmall.dao.mybatis.mapper.ReviewMapper;
import com.example.plantmall.domain.Review;

@Repository
public class MybatisReviewDao implements ReviewDao {

	@Autowired
	private ReviewMapper reviewMapper;
	
	@Override
	public List<Review> getReviewsByProductId(String productId) throws DataAccessException {
		// TODO Auto-generated method stub
		return reviewMapper.getReviewsByProductId(productId);
	}

	@Override
	public Review getReview(Review review) throws DataAccessException {
		// TODO Auto-generated method stub
		return reviewMapper.getReview(review);
	}


	@Override
	public Review getReviewByReviewId(int reviewId) throws DataAccessException {
		// TODO Auto-generated method stub
		return reviewMapper.getReviewByReviewId(reviewId);
	}
	
	@Override
	@Transactional
	public void insertReview(Review review) throws DataAccessException {
		// TODO Auto-generated method stub
		reviewMapper.insertReview(review);
	}

	@Override
	public Review updateReview(Review review) {
		// TODO Auto-generated method stub
		reviewMapper.updateReview(review);
		return reviewMapper.getReview(review);
	}

}
