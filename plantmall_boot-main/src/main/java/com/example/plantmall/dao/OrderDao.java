package com.example.plantmall.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.plantmall.domain.LineItem;
import com.example.plantmall.domain.Order;

public interface OrderDao {
	List<Order> getOrdersByUserId(String userId) throws DataAccessException;
	Order getOrder(int orderId) throws DataAccessException;
	void insertOrder(Order order) throws DataAccessException;
	LineItem getLineItem(int orderId, int lineNumber) throws DataAccessException;;
}
