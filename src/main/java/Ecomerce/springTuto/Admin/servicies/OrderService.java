package Ecomerce.springTuto.Admin.servicies;

import Ecomerce.springTuto.Admin.repositories.OrderRepository;
import Ecomerce.springTuto.Admin.models.Order;
import Ecomerce.springTuto.Admin.models.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public Order createOrder(Order order) {
        order.setStatus(OrderStatus.PENDING);
        // Vous pouvez ajouter d'autres logiques d'initialisation ici
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = getOrderById(orderId);
        if (order != null) {
            order.setStatus(status);
            return orderRepository.save(order);
        }
        return null; // Ou lancez une exception si l'ID de commande n'est pas valide
    }

    // Autres méthodes de service en fonction des besoins de votre application
}
