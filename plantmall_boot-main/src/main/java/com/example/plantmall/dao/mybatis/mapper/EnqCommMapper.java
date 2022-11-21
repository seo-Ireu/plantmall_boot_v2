package com.example.plantmall.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.plantmall.domain.EnqComm;

@Mapper
public interface EnqCommMapper {
	EnqComm getEnqCommByEuquiryId(int enquiryid);
	EnqComm getEnqCommByEnquiryCommId(int enqCommId);
	void insertEnqComm(EnqComm enqComm);
	void updateEnqComm(EnqComm enqComm);
}
