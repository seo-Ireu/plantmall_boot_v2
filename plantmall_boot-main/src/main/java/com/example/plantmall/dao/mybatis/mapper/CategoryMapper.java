
package com.example.plantmall.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.plantmall.domain.Category;

@Mapper
public interface CategoryMapper {

  List<Category> getCategoryList();

  Category getCategory(String categoryId);

}