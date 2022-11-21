package com.example.plantmall.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.plantmall.dao.FundingRelationDao;
import com.example.plantmall.dao.mybatis.mapper.FundingRelationMapper;
import com.example.plantmall.domain.FundingOrder;
@Repository
public class MybatisFundingRelationDao implements FundingRelationDao{
	@Autowired
	private FundingRelationMapper fundingRelationMapper;

	@Override
	public List<FundingOrder> getAllFundingOrderListByBuyerId(String id) {
		return fundingRelationMapper.getAllFundingOrderListByBuyerId(id);
	}

	@Override
	public FundingOrder getFundingOrder(String id) {
		return fundingRelationMapper.getFundingOrder(id);
	}

	@Override
	public void insertFundingOrder(FundingOrder fundingOrder) {
		fundingRelationMapper.insertFundingOrder(fundingOrder);
	}

	@Override
	public void deleteFundingOrder(String id) {
		fundingRelationMapper.deleteFundingOrder(id);
		
	}

	@Override
	public List<FundingOrder> getAllMyFundingOrderList(String fundingId) {
		return fundingRelationMapper.getAllMyFundingOrderList(fundingId);
	}


}
