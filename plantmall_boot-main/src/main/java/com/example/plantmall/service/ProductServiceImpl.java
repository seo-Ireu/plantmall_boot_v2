package com.example.plantmall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.plantmall.controller.Product.ProductImg;
import com.example.plantmall.controller.Product.SearchValueCommand;
import com.example.plantmall.dao.CategoryDao;
import com.example.plantmall.dao.ProductDao;
import com.example.plantmall.dao.UserDao;
import com.example.plantmall.domain.Category;
import com.example.plantmall.domain.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired   
	private CategoryDao categoryDao;
	
	@Autowired
	private UserDao userDao;

	@Autowired  
	private ProductDao productDao;
	
	public Product getProduct(String productId) {
		return productDao.getProduct(productId);
	}

	public List<Product> getProductHaveLineItem(String productId) {
		return productDao.getProductHaveLineItem(productId);
	}

	public List<Product> getProductHaveCartItem(String productId) {
		return productDao.getProductHaveCartItem(productId);
	}
	
	public Category getCategory(String categoryId) {
		return categoryDao.getCategory(categoryId);
	}
	
	public List<Category> getCategoryList() {
		return categoryDao.getCategoryList();
	}

	public List<Product> getProductListByCategory(String categoryId) {
		return productDao.getProductListByCategory(categoryId);
	}

	public List<Product> getAllProduct() {
		return productDao.getAllProduct();
	}
	
	public List<Product> searchProductList(SearchValueCommand svc) {
		return productDao.searchProductList(svc);
	}
	
	public List<Product> showProductList(String productId){
		return productDao.showProductList(productId);
	}
	
	public List<Product> getProductListforUser(String userId){
		return productDao.getProductListforUser(userId);
	}

	public void insertProduct(Product product){
		productDao.insertProduct(product);
	}
	
	public void updateProduct(Product product){
		productDao.updateProduct(product);
	}

	public void deleteProduct(Product product){
		productDao.deleteProduct(product);
	}
	
	public void deleteContentHaveProduct(Product product){
		productDao.deleteContentHaveProduct(product);
	}

	public int saveProductImage(ProductImg productImg) {
		return productDao.saveProductImage(productImg);
	}
	
	public int updateProductImage(ProductImg productImg) {
		return productDao.updateProductImage(productImg);
	}

	public int deleteProductImage(String productImgIdx) {
		return productDao.deleteProductImage(productImgIdx);
	}
	
	public Map<String, Object> selectProductImage(String productImgIdx){
		return productDao.selectProductImage(productImgIdx);
	}
	
	public String getUserName(String userId) {
		return userDao.getUserById(userId).getUserName();
	}
	
	public List<Product> getBestProductList(){
		return productDao.getBestProductList();
	}
	
}