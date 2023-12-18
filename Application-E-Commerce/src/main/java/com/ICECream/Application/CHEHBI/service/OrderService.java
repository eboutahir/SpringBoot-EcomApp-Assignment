package com.ICECream.Application.CHEHBI.service;

import java.util.List;

import com.ICECream.Application.CHEHBI.domain.Order;
import com.ICECream.Application.CHEHBI.domain.Payment;
import com.ICECream.Application.CHEHBI.domain.Shipping;
import com.ICECream.Application.CHEHBI.domain.ShoppingCart;
import com.ICECream.Application.CHEHBI.domain.User;

public interface OrderService {

	Order createOrder(ShoppingCart shoppingCart, Shipping shippingAddress, Payment payment, User user);
	
	List<Order> findByUser(User user);
	
	Order findOrderWithDetails(Long id);
}
