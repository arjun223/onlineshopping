package com.arjun.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.arjun.shoppingbackend.Dao.CategoryDao;
import com.arjun.shoppingbackend.Dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CategoryDao categoryDao;

	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.arjun.shoppingbackend");
		context.refresh();
		categoryDao = (CategoryDao) context.getBean("categoryDao");
	}

	@Test
	public void testCROUDCategory() {

		// Test add category
		category = new Category();
		category.setName("mobile");
		category.setDescription("This is some description for mobile!");
		category.setImageURL("CAT_1045.png");
		assertEquals("Successfully added a category inside the table!", true, categoryDao.add(category));

		category = new Category();
		category.setName("laptop");
		category.setDescription("This is some description for laptop!");
		category.setImageURL("CAT_1045.png");
		assertEquals("Successfully added a category inside the table!", true, categoryDao.add(category));

		// test update
		category = categoryDao.get(2);
		category.setName("Dell");
		assertEquals("Successfully update a single category in the table!", true, categoryDao.update(category));

		// delete test
		assertEquals("Successfully delete a single category in the table!", true, categoryDao.delete(category));

		// test list

		assertEquals("Successfully fetch list of category from the table!", 1, categoryDao.list().size());
	}

//	@Test
//	public void testListCategory() {
//		category = categoryDao.get(1);
//		assertEquals("Successfully fetch list of category from the table!",3,categoryDao.list().size());
//	}

//	@Test
//	public void testDeleteCategory() {
//		category = categoryDao.get(1);
//		assertEquals("Successfully delete a single category in the table!",true,categoryDao.delete(category));

	// }

//	@Test
//	public void testUpdateCategory() {
//		category = categoryDao.get(1);
//		category.setName("Dell");
//		assertEquals("Successfully update a single category in the table!",true,categoryDao.update(category));
//	
	// }

//	@Test
//	public void testGetCategory() {
//		category = categoryDao.get(1);
//		assertEquals("Successfully fetch a single category from the table!","Laptop",category.getName());
//	}

//	@Test
//	public void testAddCategory() {
//		
//		category = new Category();
//		
//		category.setName("watch");
//		category.setDescription("This is some description for sony watch!");
//		category.setImageURL("CAT_146.png");
//		
//		assertEquals("Successfully added a category inside the table!",true,categoryDao.add(category));
//		
//		
//	}

}
