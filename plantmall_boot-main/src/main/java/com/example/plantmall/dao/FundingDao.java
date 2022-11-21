package com.example.plantmall.dao;

import java.util.Date;
import java.util.List;
import com.example.plantmall.domain.Funding;
import com.example.plantmall.domain.Product;
public interface FundingDao {

	List<Funding> getAllFundingList();
	Funding getFunding(String id);
	void insertFunding(Funding funding);
	List<Product> getMyAllProductList(String email);
	void updateFunding(Funding funding);
	void deleteFunding(String id);
	List<Funding> getAllMyFundingList(String id);
	void closeFunding(Date curTime);
}
