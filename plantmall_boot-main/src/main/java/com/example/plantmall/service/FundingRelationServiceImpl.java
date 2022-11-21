package com.example.plantmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.plantmall.dao.FundingRelationDao;
import com.example.plantmall.domain.FundingOrder;
@Service
public class FundingRelationServiceImpl implements FundingRelationService{
	
	@Autowired
	FundingRelationDao fundingRelationDao;

	@Override
	public List<FundingOrder> getAllFundingOrderListByBuyerId(String id) {
		// TODO Auto-generated method stub
		return fundingRelationDao.getAllFundingOrderListByBuyerId(id);
	}

	@Override
	public FundingOrder getFundingOrder(String id) {
		// TODO Auto-generated method stub
		return fundingRelationDao.getFundingOrder(id);
	}

	@Override
	public void insertFundingOrder(FundingOrder fundingOrder) {
		// TODO Auto-generated method stub
		fundingRelationDao.insertFundingOrder(fundingOrder);
	}

	@Override
	public void deleteFundingOrder(String id) {
		// TODO Auto-generated method stub
		fundingRelationDao.deleteFundingOrder(id);
	}

	@Override
	public List<FundingOrder> getAllMyFundingOrderList(String fundingId) {
		// TODO Auto-generated method stub
		return fundingRelationDao.getAllMyFundingOrderList(fundingId);
	}
}
