package com.example.plantmall.domain;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter 
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@ToString
public class LineItem implements Serializable {
	
	private int orderId;
	private int lineNumber;
	private int quantity;
	private int unitPrice;
	private Product product;
	private String productId;
	
	public LineItem(int lineNumber, CartItem cartItem) {
		this.lineNumber = lineNumber;
	    this.quantity = cartItem.getQuantity();
	    this.unitPrice = cartItem.getUnitPrice();
	    this.product = cartItem.getProduct();
	    this.productId = cartItem.getProduct().getProductId();
	}

	public int getTotalPrice() {
		return this.unitPrice * this.quantity;
	}
}
