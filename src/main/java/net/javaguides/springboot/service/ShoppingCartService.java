package net.javaguides.springboot.service;

import net.javaguides.springboot.domain.Article;
import net.javaguides.springboot.domain.CartItem;
import net.javaguides.springboot.domain.ShoppingCart;
import net.javaguides.springboot.domain.User;


public interface ShoppingCartService {

	ShoppingCart getShoppingCart(User user);
	
	int getItemsNumber(User user);
	
	CartItem findCartItemById(Long cartItemId);
	
	CartItem addArticleToShoppingCart(Article article, User user, int qty, String size);
		
	void clearShoppingCart(User user);
	
	void updateCartItem(CartItem cartItem, Integer qty);

	void removeCartItem(CartItem cartItem);
	
}
