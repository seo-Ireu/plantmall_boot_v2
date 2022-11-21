package com.example.plantmall.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.plantmall.domain.Order;

@Mapper
public interface OrderMapper {
	List<Order> getOrdersByUserId(String userId);
	Order getOrder(int orderId);
	int insertOrder(Order order);
	Order getOrderAndLineItems(int orderId);
}
