package com.example.plantmall.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@SuppressWarnings("serial")
public class CartItem implements Serializable{
	private String userId;
	private String productId;
	private int quantity;
	private int unitPrice;
	private int listPrice;
	private Product product;
	
	public int getTotalPrice() {
		if (product != null) {
			return product.getPrice() * quantity;
		}
		else {
			return 0;
		}
	}
	
	public void incrementQuantity() {
		quantity++;
	}

	@Override
	public String toString() {
		return "CartItem [userId=" + userId + ", productId=" + productId + ", quantity=" + quantity + ", unitPrice="
				+ unitPrice + ", listPrice=" + listPrice + "]";
	}
	
	
}
