package com.example.plantmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.plantmall.dao.OrderDao;
import com.example.plantmall.domain.LineItem;
import com.example.plantmall.domain.Order;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public Order getOrder(int orderId) {
		// TODO Auto-generated method stub
		return orderDao.getOrder(orderId);
	}

	@Override
	public List<Order> getOrdersByUserId(String userId) {
		// TODO Auto-generated method stub
		return orderDao.getOrdersByUserId(userId);
	}

	@Override
	public void insertOrder(Order order) throws DataAccessException {
		// TODO Auto-generated method stub
		orderDao.insertOrder(order);
	}

	@Override
	public LineItem getLineItem(int orderId, int lineNumber) {
		// TODO Auto-generated method stub
		return orderDao.getLineItem(orderId, lineNumber);
	}
 
}
