package com.example.plantmall.controller.Funding;

import java.io.Serializable;

import com.example.plantmall.domain.Funding;

@SuppressWarnings("serial")
public class FundingForm implements Serializable{

	private Funding funding;
	private boolean newFunding;
	
	public FundingForm(Funding funding) {
		super();
		this.funding = funding;
	}
	
	public FundingForm() {
		this.funding = new Funding();
		this.newFunding = true;
	}

	public Funding getFunding() {
		return funding;
	}


	public boolean isNewFunding() {
		return newFunding;
	}

	
}
