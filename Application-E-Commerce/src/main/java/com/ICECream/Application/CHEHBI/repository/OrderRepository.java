package com.ICECream.Application.CHEHBI.repository;

import java.util.List;

import com.ICECream.Application.CHEHBI.domain.Order;
import com.ICECream.Application.CHEHBI.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

	List<Order> findByUser(User user);
	
	@EntityGraph(attributePaths = { "cartItems", "payment", "shipping" })
	Order findEagerById(Long id);

}
