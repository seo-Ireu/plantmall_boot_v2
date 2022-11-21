package com.example.plantmall.dao.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.plantmall.controller.Product.ProductImg;
import com.example.plantmall.controller.Product.SearchValueCommand;
import com.example.plantmall.domain.Product;

@Mapper
public interface ProductMapper {

	Product getProduct(String productId);
	
	List<Product> getProductHaveLineItem(String productId);
	
	List<Product> getProductHaveCartItem(String productId);
	
	List<Product> getProductListByCategory(String categoryId);
	
	List<Product> searchProductList(SearchValueCommand svc);
	
	List<Product> showProductList(String productId);
	
	List<Product> getAllProduct();
	
	List<Product> getProductListforUser(String userId);
	
	void insertProduct(Product product);
	
	void updateProduct(Product product);

	void deleteProduct(Product product);
	
	void deleteContentHaveProduct(Product product);

	List<Product> searchProductList(String keywords);

	int saveProductImage(ProductImg productImg);
	
	int updateProductImage(ProductImg productImg);

	int deleteProductImage(String productImgIdx);
	
	Map<String, Object> selectProductImage(String productImgIdx);

	List<Product> getBestProductList();

}