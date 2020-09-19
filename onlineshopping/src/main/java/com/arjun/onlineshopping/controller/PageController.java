package com.arjun.onlineshopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.arjun.onlineshopping.exception.ProductNotFoundException;
import com.arjun.shoppingbackend.Dao.CategoryDao;
import com.arjun.shoppingbackend.Dao.ProductDao;
import com.arjun.shoppingbackend.Dto.Category;
import com.arjun.shoppingbackend.Dto.Product;

@Controller
public class PageController {
	
private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ProductDao productDao;

	@RequestMapping(value = { "/", "/index", "/home" })
	ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		
		logger.info("inside pagecontroller index method  - INFO");
		logger.debug("inside pagecontroller index method  - DEBUG");

		// passing list of category
		mv.addObject("categories", categoryDao.list());

		mv.addObject("userClickHome", true);
		return mv;
	}

	@RequestMapping(value = { "/contact" })
	ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}

	@RequestMapping(value = { "/about" })
	ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}
	
	// having similar mapping our flow id
	@RequestMapping(value = { "/register" })
	ModelAndView register() {
		ModelAndView mv = new ModelAndView("page");
		return mv;
	}
	
	
	
	// access-denied page for allow permission for user and admin page
	@RequestMapping(value="/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");		
		mv.addObject("errorTitle", "Aha! Caught You.");		
		mv.addObject("errorDescription", "You are not authorized to view this page!");		
		mv.addObject("title", "403 Access Denied");		
		return mv;
	}

	/*
	 * method load to all product and base on category
	 */

	@RequestMapping(value = "/show/all/products")
	ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");

		// passing list of category
		mv.addObject("categories", categoryDao.list());

		mv.addObject("userClickAllProducts", true);
		return mv;
	}

	@RequestMapping(value = "/show/category/{id}/products")
	ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");

		// categoryDao to fetch single category
		Category category = null;
		category = categoryDao.get(id);

		mv.addObject("title", category.getName());

		// passing list of category
		mv.addObject("categories", categoryDao.list());
		
		//passing the single category objects
		mv.addObject("category", category);

		mv.addObject("userClickCategoryProducts", true);
		return mv;
	}
	
	
	/*
	 * view single product 
	 */
	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showsingleproduct(@PathVariable("id") int id) throws ProductNotFoundException {
		
		ModelAndView mv = new ModelAndView("page");
		Product product = productDao.get(id);
		
		if(product == null) throw new  ProductNotFoundException();
		
		// update the view count
		product.setViews(product.getViews()+1);
		productDao.update(product);
		
		//----------------------
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProducts", true);
		return mv;
		
	}
	
	
	// login page
	
	@RequestMapping(value = { "/login" })
	ModelAndView login(@RequestParam(name="error",required = false) String error) {
		ModelAndView mv = new ModelAndView("login");
		if(error != null) {
			mv.addObject("message", "Invaild user name and password");
		}
		mv.addObject("title", "login");
		return mv;
	}
	
	
	@RequestMapping(value="/perform-logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		// Invalidates HTTP Session, then unbinds any objects bound to it.
	    // Removes the authentication from securitycontext 		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
		
		return "redirect:/login?logout";
	}
	
	

}
