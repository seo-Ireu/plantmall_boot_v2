package com.example.plantmall.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.plantmall.domain.FundingOrder;
@Mapper
public interface FundingRelationMapper {

	List<FundingOrder> getAllFundingOrderListByBuyerId(String id);
	FundingOrder getFundingOrder(String id);
	
	void insertFundingOrder(FundingOrder fundingOrder);
	void deleteFundingOrder(String id);
	
	List<FundingOrder> getAllMyFundingOrderList(String fundingId);
	
}
