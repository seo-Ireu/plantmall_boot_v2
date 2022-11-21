package com.example.plantmall.dao;

import org.springframework.dao.DataAccessException;

import com.example.plantmall.domain.EnqComm;

public interface EnqCommDao {
	EnqComm getEnqCommByEuquiryId(int enquiryid) throws DataAccessException;
	EnqComm getEnqCommByEnquiryCommId(int enqCommId) throws DataAccessException;
	void insertEnqComm(EnqComm enqComm) throws DataAccessException;
	void updateEnqComm(EnqComm enqComm) throws DataAccessException;
}