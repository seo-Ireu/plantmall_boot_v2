package com.example.plantmall.service;

import java.util.List;

import com.example.plantmall.domain.CartItem;

public interface CartService {
	List<CartItem> getCartItemList(String userId);
	CartItem getCartItemByProductId(String productId);
	void insertCartItem(CartItem cartItem);
	void deleteCartItem(CartItem cartItem);
	void deleteCartItem(String userId, String productId);
	void updateCartItem(CartItem cartItem);
	Integer sumMoney(String userId);
}
