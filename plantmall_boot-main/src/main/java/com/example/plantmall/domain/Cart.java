package com.example.plantmall.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.support.PagedListHolder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class Cart implements Serializable {

	private Map<String, CartItem> itemMap = Collections.synchronizedMap(new HashMap<String, CartItem>());
	private PagedListHolder<CartItem> itemList = new PagedListHolder<CartItem>();

	public Iterator<CartItem> getAllCartItems() {
		return itemList.getSource().iterator();
	}

	public PagedListHolder<CartItem> getCartItemList() {
		return itemList;
	}

	public int getNumberOfCartItems() {
		return itemList.getSource().size();
	}

	public boolean containsProductId(String productId) {
		return itemMap.containsKey(productId);
	}

	public void addProduct(CartItem cartItem) {
		System.out.println("\n Cart.addProduct()");
		CartItem c = itemMap.get(cartItem.getProductId());
		if (c == null) {
			System.out.println("c == null");
			c = cartItem;
			System.out.println("addProduct - CartItem c: " + c);
			itemMap.put(c.getProductId(), c);
			itemList.getSource().add(c);
		}
	}

	public Product removeCartItemById(String produtId) {
		CartItem cartItem = itemMap.remove(produtId);
		if (cartItem == null) {
			return null;
		} else {
			itemList.getSource().remove(cartItem);
			return cartItem.getProduct();
		}
	}
	
//	public void incrementQuantityByProductId(String productId) {
//	    CartItem cartItem = itemMap.get(productId);
//	    cartItem.incrementQuantity();
//	}

	public void setQuantityByProductId(String productId, int quantity) {
//	    CartItem cartItem = itemMap.get(productId);
//	    cartItem.setQuantity(quantity);
		itemMap.get(productId).setQuantity(quantity);
	}

	public int getSubTotal() {
	    int subTotal = 0;
	    Iterator<CartItem> items = getAllCartItems();
	    while (items.hasNext()) {
	      CartItem cartItem = (CartItem) items.next();
	      Product product = cartItem.getProduct();
	      int price = product.getPrice();
	      int quantity = cartItem.getQuantity();
	      subTotal += price * quantity;
	    }
	    return subTotal;
	}

	@Override
	public String toString() {
		return "Cart [itemMap=" + itemMap + ", itemList=" + itemList + "]";
	}
	
	
}
