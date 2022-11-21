package com.example.plantmall.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter @Setter @ToString
public class Order implements Serializable{
	private int orderId;
	private String userId;
	@NotEmpty
	private String userName;
	@Pattern(regexp="^01[01679]-\\d{3,4}-\\d{4}", message="01*-****-****")
	private String phoneNumber;
	private Date orderDate;
	@NotEmpty
	private String shipAddress;
	private int totalPrice;
	@Pattern(regexp="\\d{4}-\\d{4}-\\d{4}-\\d{4}", message="****-****-****-****")
	private String creditCard;
	@Pattern(regexp="\\d{2}/\\d{2}", message="**/**")
	private String expiryDate;
	@NotEmpty
	private String cardType;
	private List<LineItem> lineItems = new ArrayList<LineItem>();
	
	public void initOrder(User user, List<LineItem> lineItems) {
		userId = user.getUserId();
		userName = user.getUserName();
		phoneNumber = user.getPhone();
		orderDate = new Date();
		
		shipAddress = user.getAddress();
		
		totalPrice = setTotalPriceUsingLineItems(lineItems);
  
	    this.lineItems = lineItems;
	}
	
	public void addLineItem(CartItem cartItem) {
	    LineItem lineItem = new LineItem(lineItems.size(), cartItem);
	    addLineItem(lineItem);
	 }

	 public void addLineItem(LineItem lineItem) {
	    lineItems.add(lineItem);
	 }
	 
	 public int setTotalPriceUsingLineItems(List<LineItem> lineItems) {
		 int sum = 0;
		 for (LineItem line : lineItems) {
			 sum += line.getTotalPrice();
		 }
		 return sum;
	 }
}
