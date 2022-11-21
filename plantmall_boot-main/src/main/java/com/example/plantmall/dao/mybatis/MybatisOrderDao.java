package com.example.plantmall.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.plantmall.dao.OrderDao;
import com.example.plantmall.dao.mybatis.mapper.LineItemMapper;
import com.example.plantmall.dao.mybatis.mapper.OrderMapper;
import com.example.plantmall.domain.LineItem;
import com.example.plantmall.domain.Order;

@Repository
public class MybatisOrderDao implements OrderDao {
	@Autowired
	protected OrderMapper orderMapper;
	@Autowired
	protected LineItemMapper lineItemMapper;

	@Override
	public List<Order> getOrdersByUserId(String userId) throws DataAccessException {
		// TODO Auto-generated method stub
		return orderMapper.getOrdersByUserId(userId);
	}

	@Override
	public Order getOrder(int orderId) throws DataAccessException {
		// TODO Auto-generated method stub
		Order order = orderMapper.getOrderAndLineItems(orderId);
		return order;
	}

	@Override
	@Transactional
	public void insertOrder(Order order) throws DataAccessException {
		// TODO Auto-generated method stub
		//order.setOrderId(sequenceDao.getNextId("ordernum"));
    	int row = orderMapper.insertOrder(order);
    	int id = order.getOrderId();
    	System.out.println(id);
    	for (int i = 0; i < order.getLineItems().size(); i++) {
    		LineItem lineItem = (LineItem) order.getLineItems().get(i);
    		lineItem.setOrderId(id);
    		lineItemMapper.insertLineItem(lineItem);
    	}
	}

	@Override
	public LineItem getLineItem(int orderId, int lineNumber) throws DataAccessException {
		// TODO Auto-generated method stub
		return lineItemMapper.getLineItem(orderId, lineNumber);
	}

	
}
