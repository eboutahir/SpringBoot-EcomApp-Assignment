package com.example.tpEcomm.services;

import com.example.tpEcomm.models.OrderItem;
import com.example.tpEcomm.repositories.OrderItemRepository;
import com.example.tpEcomm.services.OrderItemServiceImp;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@AllArgsConstructor
class OrderItemServiceImpTest {

    @Mock
    private final OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderItemServiceImp orderItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllOrderItems() {
        List<OrderItem> orderItemList = new ArrayList<>();
        when(orderItemRepository.findAll()).thenReturn(orderItemList);

        List<OrderItem> result = orderItemService.findAll();

        assertEquals(orderItemList, result);
    }

    @Test
    void testFindOrderItemById() {
        Long orderItemId = 1L;
        OrderItem expectedOrderItem = new OrderItem();
        expectedOrderItem.setId(orderItemId);

        when(orderItemRepository.findById(orderItemId)).thenReturn(Optional.of(expectedOrderItem));

        Optional<OrderItem> result = orderItemService.findById(orderItemId);

        assertTrue(result.isPresent());
        assertEquals(expectedOrderItem, result.get());
    }

    @Test
    void testSaveOrderItem() {
        OrderItem orderItemToSave = new OrderItem();
        when(orderItemRepository.save(orderItemToSave)).thenReturn(orderItemToSave);

        OrderItem savedOrderItem = orderItemService.save(orderItemToSave);

        assertEquals(orderItemToSave, savedOrderItem);
    }

    @Test
    void testDeleteOrderItemById() {
        Long orderItemId = 1L;

        assertDoesNotThrow(() -> orderItemService.deleteById(orderItemId));

        verify(orderItemRepository, times(1)).deleteById(orderItemId);
    }
}
