package com.arjun.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.arjun.shoppingbackend.Dao.CartLineDao;
import com.arjun.shoppingbackend.Dao.ProductDao;
import com.arjun.shoppingbackend.Dao.UserDao;
import com.arjun.shoppingbackend.Dto.Cart;
import com.arjun.shoppingbackend.Dto.CartLine;
import com.arjun.shoppingbackend.Dto.Product;
import com.arjun.shoppingbackend.Dto.User;

public class CartLineTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CartLineDao cartLineDao;
	private static ProductDao productDao;
	private static UserDao userDao;

	private CartLine cartLine = null;
	private Cart cart = null;
	private User user = null;
	private Product product = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.arjun.shoppingbackend");
		context.refresh();
		cartLineDao = (CartLineDao) context.getBean("cartLineDao");
		productDao = (ProductDao) context.getBean("productDao");
		userDao = (UserDao) context.getBean("userDao");
	}

	
	  @Test public void testAddCartLine() {
	  
	  user= userDao.getByEmail("arjun.neupane22.an@gmail.com");
	  
	  cart = user.getCart();
	  
	  product = productDao.get(1);
	  
	  cartLine = new CartLine();
	 
	  cartLine.setBuyingPrice(product.getUnitPrice());
	  cartLine.setProductCount(cartLine.getProductCount() +1);
	
	  cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
	  cartLine.setAvailable(true);
	  cartLine.setCartId(cart.getId());
	  cartLine.setProduct(product); 
	  assertEquals("Failed to add the CartLine!",true, cartLineDao.add(cartLine));
	  
	  
	  cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
	  cart.setCartLines(cart.getCartLines() +1);
	  assertEquals("Failed to update the cart!",true, cartLineDao.updateCart(cart));
	  
	  }
	  


}
