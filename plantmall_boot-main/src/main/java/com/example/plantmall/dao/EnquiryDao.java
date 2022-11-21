package com.example.plantmall.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.plantmall.domain.Enquiry;

public interface EnquiryDao {
	List<Enquiry> getEnquiryListByProductId(String productId) throws DataAccessException;
	Enquiry getEnquiry(Enquiry enq) throws DataAccessException;
	Enquiry getEnquiryByEnquiryId(int enq) throws DataAccessException;
	void insertEnquiry(Enquiry enq) throws DataAccessException;
	void updateEnquiry(Enquiry enq) throws DataAccessException;
	void deleteEnquiry(int enq) throws DataAccessException;
}
