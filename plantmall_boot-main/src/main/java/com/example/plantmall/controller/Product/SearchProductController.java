package com.example.plantmall.controller.Product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.plantmall.service.ProductService;
import com.example.plantmall.domain.Category;
import com.example.plantmall.domain.Product;

@Controller
public class SearchProductController {
	private ProductService productService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	//제품 검색
	@RequestMapping("/product/search")
	public String showProductSearch(ModelMap model) {
		
		List<Category> categoryList = this.productService.getCategoryList();
		
		model.put("categoryList", categoryList);
		
		System.out.println("제품 검색 카테고리 list model -> view 전달");
		
		return "product/productSearch";
	}
	
	//제품 검색 결과 
	@RequestMapping("/product/searchResult")
	public String showProductSearchResult(@ModelAttribute SearchValueCommand svc,
										ModelMap model) throws Exception {		
		List<Product> itemList;
		String categoryName;

		try {
			categoryName = this.productService.getCategory(svc.getCategoryId()).getC_name();
		} catch (NullPointerException e) {
			System.out.println("선택된 카테고리 없음");
			svc.setCategoryId(null);
			categoryName = "전체";
		}
		
		itemList = this.productService.searchProductList(svc);		
		List<List<Product>> productList = new ArrayList<>();
		List<List<String>> userNameList = new ArrayList<>();

		try {
			for (int i = 0; i < itemList.size(); i += 4) {
				List<Product> miniProductList = new ArrayList<>();
				List<String> miniUserNameList = new ArrayList<>();
				 
				productList.add(miniProductList);
				userNameList.add(miniUserNameList);
				for (int j = 0; j < 4; j++) {
					String userName = this.productService.getUserName(itemList.get(i+j).getUserId());

					miniProductList.add(itemList.get(i+j));
					miniUserNameList.add(userName);
				} 
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("더 이상 제품을 찾을 수 없음");
		}
		model.put("categoryName", categoryName);
		model.put("productList", productList);
		model.put("userNameList", userNameList);
		model.put("total", itemList.size());
		
		System.out.println("제품 검색 결과 Product list model -> view 전달");
		
		return "product/searchResult";
	}
}
