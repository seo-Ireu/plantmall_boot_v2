package com.example.plantmall.dao;

import java.util.List;

import com.example.plantmall.domain.FundingOrder;

public interface FundingRelationDao {
	List<FundingOrder> getAllFundingOrderListByBuyerId(String id);
	FundingOrder getFundingOrder(String id);
	
	void insertFundingOrder(FundingOrder fundingOrder);
	void deleteFundingOrder(String id);
	
	List<FundingOrder> getAllMyFundingOrderList(String fundingId);
}
