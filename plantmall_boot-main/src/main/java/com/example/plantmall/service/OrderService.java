package com.example.plantmall.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.plantmall.domain.LineItem;
import com.example.plantmall.domain.Order;

public interface OrderService {
	Order getOrder(int orderId);
	void insertOrder(Order order) throws DataAccessException;
	public List<Order> getOrdersByUserId(String userId);
	public LineItem getLineItem(int orderId, int lineNumber);
	
}
