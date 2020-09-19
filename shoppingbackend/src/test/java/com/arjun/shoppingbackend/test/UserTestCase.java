package com.arjun.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.arjun.shoppingbackend.Dao.UserDao;
import com.arjun.shoppingbackend.Dto.Address;
import com.arjun.shoppingbackend.Dto.Cart;
import com.arjun.shoppingbackend.Dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;

	private static UserDao userDao;

	private User user;
	private Address address;
	private Cart cart;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.arjun.shoppingbackend");
		context.refresh();
		userDao = (UserDao) context.getBean("userDao");
	}

//	@Test
//	public void testAddUser() {
//		
//		user = new User() ;
//		user.setFirstName("Hrithik");
//		user.setLastName("Roshan");
//		user.setEmail("hr@gmail.com");
//		user.setContactNumber("1234512345");
//		user.setRole("USER");
//		user.setEnabled(true);
//		user.setPassword("12345");
//		assertEquals("error added a User inside the table!",true,userDao.addUser(user));
//		
//		address = new Address();
//		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
//		address.setAddressLineTwo("Near Kaabil Store");
//		address.setCity("Mumbai");
//		address.setState("Maharashtra");
//		address.setCountry("India");
//		address.setPostalCode("400001");
//		address.setBilling(true);
//		
//		address.setUserId(user.getId());
//		
//		assertEquals("FAIELD added a address inside the table!",true,userDao.addAddress(address));
//		
//		
//		if(user.getRole().equals("USER")) {	
//			Cart cart = new Cart();
//			cart.setUser(user);
//			assertEquals("faield added a address inside the table!",true,userDao.addCart(cart));
//			
//			
//			
//			// add the shipping address
//			address = new Address();
//			address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
//			address.setAddressLineTwo("Near Kudrat Store");
//			address.setCity("Mumbai");
//			address.setState("Maharashtra");
//			address.setCountry("India");
//			address.setPostalCode("400001");
//			
//			
//			address.setUserId(user.getId());
//			assertEquals("Failed to add the shipping address!", true, userDao.addAddress(address));
//		}	
//		
//	}

//	@Test
//	public void testAddUser() {
//		
//		user = new User() ;
//		user.setFirstName("Hrithik");
//		user.setLastName("Roshan");
//		user.setEmail("hr@gmail.com");
//		user.setContactNumber("1234512345");
//		user.setRole("USER");
//		user.setEnabled(true);
//		user.setPassword("12345");
//		
//		
//		if(user.getRole().equals("USER")) {	
//			Cart cart = new Cart();
//			cart.setUser(user);
//			
//			user.setCart(cart);
//		
//	}
//	
//		assertEquals("error added a User inside the table!",true,userDao.addUser(user));
//}

//	@Test
//	public void testAddUser() {
//		user = userDao.getByEmail("hr@gmail.com");
//		
//		cart= user.getCart();
//		cart.setGrandTotal(5555);
//		cart.setCartLines(2);
//		
//		
//		assertEquals("faield added a address inside the table!",true,userDao.updateCart(cart));	
//		
//	}

//	@Test
//	public void testAddUser() {
//		
//		user = new User() ;
//		user.setFirstName("Hrithik");
//		user.setLastName("Roshan");
//		user.setEmail("hr@gmail.com");
//		user.setContactNumber("1234512345");
//		user.setRole("USER");
//		user.setEnabled(true);
//		user.setPassword("12345");
//		assertEquals("error added a User inside the table!",true,userDao.addUser(user));
//		
//		address = new Address();
//		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
//		address.setAddressLineTwo("Near Kaabil Store");
//		address.setCity("Mumbai");
//		address.setState("Maharashtra");
//		address.setCountry("India");
//		address.setPostalCode("400001");
//		address.setBilling(true);
//		
//		address.setUser(user);
//		assertEquals("FAIELD added a address inside the table!",true,userDao.addAddress(address));
//		
//			
//		// add the shipping address
//		address = new Address();
//		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
//		address.setAddressLineTwo("Near Kudrat Store");
//		address.setCity("Mumbai");
//		address.setState("Maharashtra");
//		address.setCountry("India");
//		address.setPostalCode("400001");
//		
//		address.setUser(user);
//		assertEquals("FAIELD added a address inside the table!",true,userDao.addAddress(address));
//			
//		
//}

//	@Test
//	public void testAddUser() {
//		
//		user = userDao.getByEmail("hr@gmail.com");
//		address = new Address();
//		address.setAddressLineOne("345/B Jadoo Society, Kishan Kanhaiya Nagar");
//		address.setAddressLineTwo("Near Kudrat Store");
//		address.setCity("kathamdu");
//		address.setState("newroad");
//		address.setCountry("nepal");
//		address.setPostalCode("400001");
//		
//		address.setUser(user);
//		assertEquals("FAIELD added a address inside the table!",true,userDao.addAddress(address));
//			
//		
//		
//		
//	}

	@Test
	public void testAddUser() {

		user = userDao.getByEmail("hr@gmail.com");
		assertEquals("Successfully fetch list of category from the table!", 0,
				userDao.listSheppingAddress(user).size());
		
		
		user = userDao.getByEmail("hr@gmail.com");
		assertEquals("Successfully fetch list of category from the table!", "Mumbai",
				userDao.getbillingAddress(user).getCity());

	}

}
