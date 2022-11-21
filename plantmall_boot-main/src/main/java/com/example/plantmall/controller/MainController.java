package com.example.plantmall.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.plantmall.domain.Funding;
import com.example.plantmall.domain.Product;
import com.example.plantmall.service.FundingService;
import com.example.plantmall.service.ProductService;

@Controller
public class MainController {
	@Autowired
	private ProductService productService;

	@Autowired
	private FundingService fundingService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping("/")
	public String showImages(ModelMap model) throws Exception {

		List<Product> bestItemList;
		List<Product> bestItemList5 = new ArrayList<>();

		Product plant = new Product();
		Product gardeningSupplies = new Product();
		Funding funding = new Funding();
		Product productHaveFunding = new Product();

		try {
			plant = this.productService.getAllProduct().get(0);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("모든 제품을 찾을 수 없음");
		}
		try {
			gardeningSupplies = this.productService.getProductListByCategory("c7").get(0);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("원예용품 카테고리 제품을 찾을 수 없음");
		}
		try {
			funding = this.fundingService.getAllFundingList().get(0);
			productHaveFunding = this.productService.getProduct(funding.getProductId());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("펀딩 제품을 찾을 수 없음");
		}
		try {
			bestItemList = this.productService.getBestProductList();

			for (int i = 0; i < 5; i++)
				bestItemList5.add(bestItemList.get(i));

		} catch (IndexOutOfBoundsException e) {
			System.out.println("더 이상 제품을 찾을 수 없음");
		}
		model.put("bestItemList5", bestItemList5);
		model.put("plant", plant);
		model.put("gardeningSupplies", gardeningSupplies);
		model.put("productHaveFunding", productHaveFunding);

		return "main";
	}
}
