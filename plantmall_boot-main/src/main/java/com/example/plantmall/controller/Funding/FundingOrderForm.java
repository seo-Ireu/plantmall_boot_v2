package com.example.plantmall.controller.Funding;

import java.io.Serializable;

import javax.validation.Valid;

import com.example.plantmall.domain.FundingOrder;


@SuppressWarnings("serial")
public class FundingOrderForm implements Serializable {
	private FundingOrder fundingOrder;
	
	public FundingOrderForm(FundingOrder fundingOrder) {
		super();
		this.fundingOrder = fundingOrder;
	}
	
	public FundingOrderForm() {
		this.fundingOrder = new FundingOrder();
	}

	public FundingOrder getFundingOrder() {
		return fundingOrder;
	}

}
