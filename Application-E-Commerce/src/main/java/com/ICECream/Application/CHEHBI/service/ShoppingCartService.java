package com.ICECream.Application.CHEHBI.service;

import com.ICECream.Application.CHEHBI.domain.Article;
import com.ICECream.Application.CHEHBI.domain.CartItem;
import com.ICECream.Application.CHEHBI.domain.ShoppingCart;
import com.ICECream.Application.CHEHBI.domain.User;


public interface ShoppingCartService {

	ShoppingCart getShoppingCart(User user);
	
	int getItemsNumber(User user);
	
	CartItem findCartItemById(Long cartItemId);
	
	CartItem addArticleToShoppingCart(Article article, User user, int qty, String size);
		
	void clearShoppingCart(User user);
	
	void updateCartItem(CartItem cartItem, Integer qty);

	void removeCartItem(CartItem cartItem);
	
}
