package com.arjun.shoppingbackend.Dao;

import java.util.List;

import com.arjun.shoppingbackend.Dto.Cart;
import com.arjun.shoppingbackend.Dto.CartLine;

public interface CartLineDao {
	
	public List<CartLine> list(int cartId);
	public CartLine get(int id);	
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean delete(CartLine cartLine);
	
	// fetch the CartLine based on cartId and productId
	public CartLine getByCartAndProduct(int cartId, int productId);		
		
	// updating the cart
	boolean updateCart(Cart cart);
	// list of available cartLine
	public List<CartLine> listAvailable(int cartId);
	

}
