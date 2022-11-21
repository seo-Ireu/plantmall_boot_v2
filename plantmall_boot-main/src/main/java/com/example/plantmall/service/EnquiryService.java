package com.example.plantmall.service;

import java.util.List;

import com.example.plantmall.domain.EnqComm;
import com.example.plantmall.domain.Enquiry;

public interface EnquiryService {
	List<Enquiry> getEnquiryListByProductId(String productId);
	Enquiry getEnquiry(Enquiry enq);
	Enquiry getEnquiryByEnquiryId(int enq);
	void insertEnquiry(Enquiry enq);
	void updateEnquiry(Enquiry enq);
	void deleteEnquiry(int enq);
	
	EnqComm getEnqCommByEuquiryId(int enquiryid);
	EnqComm getEnqCommByEnquiryCommId(int enqCommId);
	void insertEnqComm(EnqComm enqComm);
	void updateEnqComm(EnqComm enqComm);
}
