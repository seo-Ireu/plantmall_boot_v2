package com.example.plantmall.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.example.plantmall.controller.Product.ProductImg;
import com.example.plantmall.controller.Product.SearchValueCommand;
import com.example.plantmall.domain.Product;

public interface ProductDao {

	Product getProduct(String productId) throws DataAccessException;

	List<Product> getProductHaveLineItem(String productId) throws DataAccessException;

	List<Product> getProductHaveCartItem(String productId) throws DataAccessException;

	List<Product> getProductListByCategory(String categoryId) throws DataAccessException;

	List<Product> searchProductList(SearchValueCommand svc) throws DataAccessException;
	
	List<Product> showProductList(String productId) throws DataAccessException;

	List<Product> getAllProduct() throws DataAccessException;

	List<Product> getProductListforUser(String userId);
	
	void insertProduct(Product product) throws DataAccessException;
	
	void updateProduct(Product product) throws DataAccessException;

	void deleteProduct(Product product) throws DataAccessException;

	void deleteContentHaveProduct(Product product) throws DataAccessException;
	
	int saveProductImage(ProductImg productImg) throws DataAccessException;
	
	int updateProductImage(ProductImg productImg) throws DataAccessException;
	
	int deleteProductImage(String productImgIdx) throws DataAccessException;
	
	Map<String, Object> selectProductImage(String productImgIdx) throws DataAccessException;

	List<Product> getBestProductList() throws DataAccessException;
}