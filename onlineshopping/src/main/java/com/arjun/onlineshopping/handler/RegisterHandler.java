package com.arjun.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.arjun.onlineshopping.model.RegisterModel;
import com.arjun.shoppingbackend.Dao.UserDao;
import com.arjun.shoppingbackend.Dto.Address;
import com.arjun.shoppingbackend.Dto.Cart;
import com.arjun.shoppingbackend.Dto.User;

@Component
public class RegisterHandler {

	@Autowired
	private UserDao dao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public RegisterModel init() {

		return new RegisterModel();

	}

	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}

	public void addBilling(RegisterModel registerModel, Address billing) {
		registerModel.setBilling(billing);
	}

	// add user in database
	public String saveAll(RegisterModel registerModel) {
		String transitionValue = "success";
		User user = registerModel.getUser();
		if (user.getRole().equals("USER")) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		 }  
		
		user.setPassword(encoder.encode(user.getPassword()));
		dao.addUser(user);

		Address billing = registerModel.getBilling();

		billing.setUserId(user.getId());
		// billing.setUser(user);
		billing.setBilling(true);

		dao.addAddress(billing);
		return transitionValue;

	}

	public String validateUser(User user, MessageContext error) {
		String transitionValue = "success";

		if (!(user.getPassword().equals(user.getConfirmPassword()))) {
			error.addMessage(new MessageBuilder().error().source("confirmPassword")
					.defaultText("password does not match!").build());
			
			transitionValue = "failure";
		}
		
		
		if(dao.getByEmail(user.getEmail()) != null) {
			error.addMessage(new MessageBuilder().error().source("email")
					.defaultText("email is already exits!").build());
			
			transitionValue = "failure";
		
		
		}
		

		return transitionValue;
		
		
		
	}
	
	

}