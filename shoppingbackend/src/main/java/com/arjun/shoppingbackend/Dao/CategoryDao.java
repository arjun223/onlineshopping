package com.arjun.shoppingbackend.Dao;

import java.util.List;

import com.arjun.shoppingbackend.Dto.Category;

public interface CategoryDao {

	List<Category> list();

	Category get(int id);

	boolean add(Category category);

	boolean update(Category category);

	boolean delete(Category category);

}
