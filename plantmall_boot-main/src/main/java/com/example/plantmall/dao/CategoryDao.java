package com.example.plantmall.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.plantmall.domain.Category;

public interface CategoryDao {

	Category getCategory(String categoryId) throws DataAccessException;

	List<Category> getCategoryList() throws DataAccessException;

}
