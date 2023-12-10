package com.example.tpEcomm.services;

import com.example.tpEcomm.models.Orderr;
import com.example.tpEcomm.models.Role;
import com.example.tpEcomm.models.User;
import com.example.tpEcomm.repositories.OrderRepository;
import com.example.tpEcomm.repositories.UserRepository;
import com.example.tpEcomm.services.OrderServiceImp;
import com.example.tpEcomm.services.interfaces.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceImpTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderService orderService = new OrderServiceImp(orderRepository, userRepository);

    @Test
    public void testFindAllOrders() {
        User user = new User("john_doe", "securePassword", "john@example.com", Role.USER);
        Orderr order1 = new Orderr(user, null, 59.98f, new Date(), false);
        Orderr order2 = new Orderr(user, null, 29.99f, new Date(), false);
        List<Orderr> orderList = Arrays.asList(order1, order2);
        when(orderRepository.findAll()).thenReturn(orderList);
        List<Orderr> result = orderService.findAll();
        assertEquals(orderList.size(), result.size());
        assertEquals(orderList.get(0).getUserinfo(), result.get(0).getUserinfo());
        assertEquals(orderList.get(0).getTotalPrice(), result.get(0).getTotalPrice());
        assertEquals(orderList.get(1).getTotalPrice(), result.get(1).getTotalPrice());
    }

    @Test
    public void testSaveOrder() {
        User user = new User("john_doe", "securePassword", "john@example.com", Role.USER);
        Orderr order = new Orderr(user, null, 36.30f, new Date(), false);

        when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
        when(orderRepository.save(Mockito.any(Orderr.class))).thenReturn(order);
        Orderr savedOrder = orderService.save(order);
        assertEquals(order.getUserinfo(), savedOrder.getUserinfo());
        assertEquals(order.getTotalPrice(), savedOrder.getTotalPrice());
        assertEquals(order.getOrderDate(), savedOrder.getOrderDate());
        assertEquals(order.isDeleted(), savedOrder.isDeleted());
    }



    @Test
    public void testDeleteOrderById() {
        Long orderId = 1L;

        orderService.deleteById(orderId);
        verify(orderRepository, times(1)).deleteById(orderId);
    }
}
