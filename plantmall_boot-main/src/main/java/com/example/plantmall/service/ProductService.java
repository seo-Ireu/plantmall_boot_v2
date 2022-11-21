package com.example.plantmall.service;

import java.util.List;
import java.util.Map;

import com.example.plantmall.controller.Product.ProductImg;
import com.example.plantmall.controller.Product.SearchValueCommand;
import com.example.plantmall.domain.Category;
import com.example.plantmall.domain.Product;

public interface ProductService {

	public Product getProduct(String productId);

	public List<Product> getProductHaveLineItem(String productId);
	
	public List<Product> getProductHaveCartItem(String productId);
	
	public Category getCategory(String categoryId);
	
	public List<Category> getCategoryList();

	public List<Product> getProductListByCategory(String categoryId);
	
	public List<Product> getAllProduct();
	
	public List<Product> searchProductList(SearchValueCommand svc);
	
	public List<Product> showProductList(String productId);
	
	public List<Product> getProductListforUser(String userId);

	public void insertProduct(Product product);
	
	public void updateProduct(Product product);
	
	public void deleteProduct(Product product);

	public void deleteContentHaveProduct(Product product);

	public int saveProductImage(ProductImg productImg);
	
	public int updateProductImage(ProductImg productImg);
	
	public int deleteProductImage(String productImgIdx);
	
	public Map<String, Object> selectProductImage(String productImgIdx);

	public String getUserName(String userId);

	public List<Product> getBestProductList();

}
