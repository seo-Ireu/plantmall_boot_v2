package com.example.plantmall.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.plantmall.domain.Enquiry;

@Mapper
public interface EnquiryMapper {
	List<Enquiry> getEnquiryListByProductId(String productId);
	Enquiry getEnquiry(Enquiry enq);
	Enquiry getEnquiryByEnquiryId(int enq);
	void insertEnquiry(Enquiry enq);
	void updateEnquiry(Enquiry enq);
	void deleteEnquiry(int enq);
}
