package com.arjun.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.arjun.shoppingbackend.Dao.ProductDao;
import com.arjun.shoppingbackend.Dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;

	private static ProductDao productDao;

	private Product product;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.arjun.shoppingbackend");
		context.refresh();
		productDao = (ProductDao) context.getBean("productDao");
	}

	/*	@Test
	public void testCRUDProduct() {
		
		// create operation
		product = new Product();
				
		product.setName("nokia 3345");
		product.setBrand("Nokia");
		product.setDescription("This is some description for Nokia mobile phones!");
		product.setUnitPrice(2000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		
		assertEquals("Something went wrong while inserting a new product!",
				true,productDao.add(product));		
		
		
		// reading and updating the category
		product = productDao.get(3);
		product.setName("Samsung Galaxy S9");
		assertEquals("Something went wrong while updating the existing record!",
				true,productDao.update(product));		
				
		assertEquals("Something went wrong while deleting the existing record!",
				true,productDao.delete(product));		
		
		// list
		assertEquals("Something went wrong while fetching the list of products!",
				6,productDao.list().size());		
				
	}		

*/

	@Test
	public void testListActiveProducts() {
		assertEquals("Something went wrong while fetching the list of products!",
				5,productDao.listActiveProducts().size());				
	} 
	
	
	@Test
	public void testListActiveProductsByCategory() {
		assertEquals("Something went wrong while fetching the list of products!",
				3,productDao.ActiveProductsByCategory(3).size());
		assertEquals("Something went wrong while fetching the list of products!",
				2,productDao.ActiveProductsByCategory(1).size());
	} 
	
	
	@Test
	public void testGetLatestActiveProduct() {
		assertEquals("Something went wrong while fetching the list of products!",
				3,productDao.getLatestActiveProducts(3).size());
		
	} 

}
