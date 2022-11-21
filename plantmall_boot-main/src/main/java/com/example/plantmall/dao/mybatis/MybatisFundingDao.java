package com.example.plantmall.dao.mybatis;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.plantmall.dao.FundingDao;
import com.example.plantmall.dao.mybatis.mapper.FundingMapper;
import com.example.plantmall.domain.Funding;
import com.example.plantmall.domain.Product;

@Repository
public class MybatisFundingDao implements FundingDao{

	@Autowired
	private FundingMapper fundingMapper;

	@Override
	public List<Funding> getAllFundingList() {
		return fundingMapper.getAllFundingList();
	}

	@Override
	public Funding getFunding(String id) {
		return fundingMapper.getFundingById(id);
	}

	@Override
	public void insertFunding(Funding funding) {
		fundingMapper.insertFunding(funding);
	}
	
	@Override
	public List<Product> getMyAllProductList(String email) {
		return fundingMapper.getMyAllProductListByUserEmail(email);
	}

	@Override
	public void updateFunding(Funding funding) {
		fundingMapper.updateFunding(funding);
		
	}

	@Override
	public void deleteFunding(String id) {
		fundingMapper.deleteFunding(id);
		
	}

	@Override
	public List<Funding> getAllMyFundingList(String id) {
		return fundingMapper.getAllMyFundingList(id);
		
	}

	@Override
	public void closeFunding(Date curTime) {
		fundingMapper.closeFunding(curTime);
		
	}


}
