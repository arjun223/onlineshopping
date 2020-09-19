package com.arjun.shoppingbackend.Dao;

import java.util.List;

import com.arjun.shoppingbackend.Dto.Address;
import com.arjun.shoppingbackend.Dto.Cart;
import com.arjun.shoppingbackend.Dto.User;

public interface UserDao {

	boolean addUser(User user);
	
	boolean addAddress(Address address);
	
	Address getbillingAddress(User user);
	
	List<Address>listSheppingAddress(User user);
	
	
	
	User getByEmail(String email);
	
}
