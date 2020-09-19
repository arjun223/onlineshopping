package com.arjun.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.arjun.onlineshopping.util.FileUploadUtility;
import com.arjun.onlineshopping.validator.ProductValidator;
import com.arjun.shoppingbackend.Dao.CategoryDao;
import com.arjun.shoppingbackend.Dao.ProductDao;
import com.arjun.shoppingbackend.Dto.Category;
import com.arjun.shoppingbackend.Dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private ProductDao productDao;

	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView ShowManagementroduct(@RequestParam(name = "operation", required = false) String operation) {
		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Products");

		Product nproduct = new Product();
		nproduct.setSupplierId(1);
		nproduct.setActive(true);
		mv.addObject("product", nproduct);

		if (operation != null) {

			if (operation.equals("product")) {
				mv.addObject("message", "product submited successfully");
			}
			else if(operation.equals("category")) {
				mv.addObject("message", "category submited successfully");
			}
		}

		return mv;

	}
	
	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView EditManagementroduct(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Products");
		
        // fetch the data from database
		Product nproduct =  productDao.get(id);
		//set the fetch data from database
		mv.addObject("product", nproduct);

		

		return mv;

	}
	
	

	@ModelAttribute("categories")
	public List<Category> getcategory() {
		return categoryDao.list();
	}
	
	@ModelAttribute("category")
	public Category getCategory() {
		return  new Category();
	}
	
	
	// handling add category submisslon on manageproduct.jsp page
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String addCategory(@ModelAttribute Category category) {
		categoryDao.add(category);
		
		return "redirect:/manage/products?operation=category";
	}
	
	
	

	// handling prodcut subsmission and handing image file

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductsubmission(@Valid @ModelAttribute("product") Product mproduct, BindingResult result,
			Model model, HttpServletRequest request) {
       
		// handling image validation from product 
		if(mproduct.getId()== 0) {
		new ProductValidator().validate(mproduct, result);
         }
         else {
        	 if(!mproduct.getFile().getOriginalFilename().equals("")) {
        		 new ProductValidator().validate(mproduct, result); 
        	 }
         }
		// checked if there are any error
		if (result.hasErrors()) {

			model.addAttribute("userClickManageProduct", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for product submission !");
			return "page";
		}
		
		logger.info(mproduct.toString());

		// create new product record
		if(mproduct.getId() == 0) {
		// create new product  if id is 0
		productDao.add(mproduct);
		}
		else {
			// update the product if id is not 0
			productDao.update(mproduct);
		}
		
		
		if (!mproduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mproduct.getFile(), mproduct.getCode());
			
		}


		return "redirect:/manage/products?operation=product";

	}
	
// active and deactive prouduct
	
	@RequestMapping(value = "/product/{id}/activation",method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable  int id){
		// is going to fetch the product from database
		Product product = productDao.get(id);
		//acitve and dactive value base on the base of active field
		boolean isActive = product.isActive();
		product.setActive(!product.isActive());
		// update product
		productDao.update(product);
		return (isActive)? " You are successfully deactive the product  id: " + product.getId()
		: " You are successfully active the product  id :" + product.getId();
	}

}
