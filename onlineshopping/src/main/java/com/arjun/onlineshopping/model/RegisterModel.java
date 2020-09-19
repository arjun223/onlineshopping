package com.arjun.onlineshopping.model;

import java.io.Serializable;

import com.arjun.shoppingbackend.Dto.Address;
import com.arjun.shoppingbackend.Dto.User;

public class RegisterModel  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	private Address billing;
	
	
	public Address getBilling() {
		return billing;
	}
	public void setBilling(Address billing) {
		this.billing = billing;
	}

}
