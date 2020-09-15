package com.arjun.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjun.shoppingbackend.Dao.ProductDao;
import com.arjun.shoppingbackend.Dto.Product;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {
 
	@Autowired
	ProductDao productDao;
	
	@RequestMapping("/all/products")
	@ResponseBody
	public List <Product>getAllProduct(){
		return productDao.listActiveProducts();
	}
	
	
	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List <Product>getProuctByCategory(@PathVariable int id){
		return productDao.ActiveProductsByCategory(id);
	}
}
