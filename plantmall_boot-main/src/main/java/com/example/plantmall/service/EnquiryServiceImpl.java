package com.example.plantmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.plantmall.dao.EnqCommDao;
import com.example.plantmall.dao.EnquiryDao;
import com.example.plantmall.domain.EnqComm;
import com.example.plantmall.domain.Enquiry;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private EnquiryDao enqDao;
	@Autowired
	private EnqCommDao enqCommDao;
	
	@Override
	public List<Enquiry> getEnquiryListByProductId(String productId) {
		// TODO Auto-generated method stub
		return enqDao.getEnquiryListByProductId(productId);
	}

	@Override
	public Enquiry getEnquiry(Enquiry enq) {
		// TODO Auto-generated method stub
		return enqDao.getEnquiry(enq);
	}

	@Override
	public Enquiry getEnquiryByEnquiryId(int enq) {
		// TODO Auto-generated method stub
		return enqDao.getEnquiryByEnquiryId(enq);
	}

	@Override
	public void insertEnquiry(Enquiry enq) {
		// TODO Auto-generated method stub
		enqDao.insertEnquiry(enq);
	}

	@Override
	public void updateEnquiry(Enquiry enq) {
		// TODO Auto-generated method stub
		enqDao.updateEnquiry(enq);
	}

	@Override
	public void deleteEnquiry(int enq) {
		// TODO Auto-generated method stub
		enqDao.deleteEnquiry(enq);
	}

	
	
	/* Enquiry Comment 관련 서비스 */
	@Override
	public EnqComm getEnqCommByEuquiryId(int enquiryid) {
		// TODO Auto-generated method stub
		return enqCommDao.getEnqCommByEuquiryId(enquiryid);
	}

	@Override
	public EnqComm getEnqCommByEnquiryCommId(int enqCommId) {
		// TODO Auto-generated method stub
		return enqCommDao.getEnqCommByEnquiryCommId(enqCommId);
	}

	@Override
	public void insertEnqComm(EnqComm enqComm) {
		// TODO Auto-generated method stub
		enqCommDao.insertEnqComm(enqComm);
	}

	@Override
	public void updateEnqComm(EnqComm enqComm) {
		// TODO Auto-generated method stub
		enqCommDao.updateEnqComm(enqComm);
	}

}
