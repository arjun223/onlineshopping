package com.arjun.shoppingbackend.DaoImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.arjun.shoppingbackend.Dao.CategoryDao;
import com.arjun.shoppingbackend.Dto.Category;

@Repository("CategoryDao")
public class CategoryDaoImpl implements CategoryDao {

	private static List<Category> categories =new ArrayList<Category>();
	
	static {
		
		//first category
		Category category = new Category();
		category.setId(1);
		category.setName("TV");
		category.setDecription("this is good product for your tv");
		category.setImageURL("cat_1.png");
		
		categories.add(category);
		
		
		
		
		//second category
		category = new Category();
		category.setId(2);
		category.setName("mobile");
		category.setDecription("this is good product for your mobile");
		category.setImageURL("cat_2.png");
		
		categories.add(category);
		
		
		//third category
		category = new Category();
		category.setId(3);
		category.setName("dvd");
		category.setDecription("this is good product for your dvd");
		category.setImageURL("cat_3.png");
		
		categories.add(category);
		
		//four category
		category = new Category();
		category.setId(4);
		category.setName("hedset");
		category.setDecription("this is good product for your hedser");
		category.setImageURL("cat_4.png");
		
		categories.add(category);
		
	}

	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}
	

}

