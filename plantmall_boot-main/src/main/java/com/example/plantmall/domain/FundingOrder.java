package com.example.plantmall.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.plantmall.domain.LineItem;
import com.example.plantmall.domain.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter @Setter @ToString
public class FundingOrder implements Serializable{
	private String fundingRelationId;
	private String userId;
	private String status;
	private String fundingId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date create_at;
	@Pattern(regexp="\\d{4}-\\d{4}-\\d{4}-\\d{4}", message="****-****-****-****")
	private String creditCard;
	@Pattern(regexp="\\d{2}/\\d{2}", message="**/**")
	private String expiryDate;
	@NotEmpty
	private String cardType;
	private int quantity;
	private int totalPrice;
	
	private String userName;
	private String phoneNumber;
	private String shipAddress;
	
	private Funding funding;
	
	public void initOrder(User user, String fundingId, int quantity, int price,String creditCard, String expiryDate) {
		userId = user.getUserId();
		this.fundingId = fundingId;
		this.quantity = quantity;	
		totalPrice = price * quantity;
		userName = user.getUserName();
		phoneNumber = user.getPhone();
		shipAddress = user.getAddress();
		this.creditCard = creditCard;
		this.expiryDate = expiryDate;
	}
}
