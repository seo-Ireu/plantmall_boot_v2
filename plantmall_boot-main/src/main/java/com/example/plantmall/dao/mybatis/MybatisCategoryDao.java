package com.example.plantmall.dao.mybatis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.example.plantmall.dao.CategoryDao;
import com.example.plantmall.dao.mybatis.mapper.CategoryMapper;
import com.example.plantmall.domain.Category;

@Repository
public class MybatisCategoryDao implements CategoryDao {
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public Category getCategory(String categoryId) throws DataAccessException {
		return categoryMapper.getCategory(categoryId);
	}
	
	@Override
	public List<Category> getCategoryList() throws DataAccessException {
		return categoryMapper.getCategoryList();
	}

}