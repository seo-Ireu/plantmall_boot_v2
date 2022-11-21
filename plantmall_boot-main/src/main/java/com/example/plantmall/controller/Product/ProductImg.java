package com.example.plantmall.controller.Product;

import lombok.Data;

@Data
public class ProductImg {
	/**
	 * [클래스 설명]
	 * 1. insert 포맷 형태 정의 클래스
	 * 2. controller 에서 사용한다.
	 */
	
	private String productImgIdx;
	private byte[] productImgValue;
	
	public ProductImg() {}
	
	public ProductImg(String productImgIdx, byte[] productImgValue) {
		this.productImgIdx = productImgIdx;
		this.productImgValue = productImgValue;
	}
}
