package com.example.plantmall.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.plantmall.dao.EnqCommDao;
import com.example.plantmall.dao.mybatis.mapper.EnqCommMapper;
import com.example.plantmall.domain.EnqComm;

@Repository
public class MybatisEnqCommDao implements EnqCommDao{

	@Autowired
	private EnqCommMapper enqCommMapper;
	
	@Override
	public EnqComm getEnqCommByEuquiryId(int enquiryid) throws DataAccessException {
		// TODO Auto-generated method stub
		return enqCommMapper.getEnqCommByEuquiryId(enquiryid);
	}

	@Override
	public EnqComm getEnqCommByEnquiryCommId(int enqCommId) throws DataAccessException {
		// TODO Auto-generated method stub
		return enqCommMapper.getEnqCommByEnquiryCommId(enqCommId);
	}

	@Override
	@Transactional
	public void insertEnqComm(EnqComm enqComm) throws DataAccessException {
		// TODO Auto-generated method stub
		enqCommMapper.insertEnqComm(enqComm);
	}

	@Override
	@Transactional
	public void updateEnqComm(EnqComm enqComm) throws DataAccessException {
		// TODO Auto-generated method stub
		enqCommMapper.updateEnqComm(enqComm);
	}

}
