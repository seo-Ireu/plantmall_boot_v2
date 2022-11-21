package com.example.plantmall.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.plantmall.domain.LineItem;

@Mapper
public interface LineItemMapper {
	
	List<LineItem> getLineItemsByOrderId(int orderId);
	LineItem getLineItem(int orderId, int lineNumber);
	void insertLineItem(LineItem lineItem);
}
