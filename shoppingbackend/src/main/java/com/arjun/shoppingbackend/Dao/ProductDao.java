package com.arjun.shoppingbackend.Dao;

import java.util.List;

import com.arjun.shoppingbackend.Dto.Product;


public interface ProductDao {
	
	List<Product> list();

	Product get(int ProductId);

	boolean add(Product product);

	boolean update(Product product);

	boolean delete(Product product);
	
	
	// business methods
	
	List<Product> listActiveProducts();
	List<Product> ActiveProductsByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);
	


}
