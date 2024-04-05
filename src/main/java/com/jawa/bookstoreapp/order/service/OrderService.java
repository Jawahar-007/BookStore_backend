package com.jawa.bookstoreapp.order.service;

import com.jawa.bookstoreapp.cart.entity.CartEntity;
import com.jawa.bookstoreapp.cart.repository.CartRepository;
import com.jawa.bookstoreapp.order.dto.OrderDTO;
import com.jawa.bookstoreapp.order.entity.OrderEntity;
import com.jawa.bookstoreapp.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService implements IOrderService{
    //
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;

    @Transactional
    @Override
    public OrderEntity placeOrder(OrderDTO orderDTO) {
        CartEntity cartcheck = cartRepository.findById(orderDTO.getCartId()).orElseThrow(()-> new RuntimeException("Cart Not Found"));

        OrderEntity order = new OrderEntity();

        order.setCart(cartcheck);
        order.setOrderDate(LocalDateTime.now());
        order.setCancel(false);
        order.setAddress(orderDTO.getAddress());

        return orderRepository.save(order);
    }

    @Transactional
    @Override
    public boolean cancelOrder(Long orderId) {

        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        if(!order.getCancel())
        {
             order.setCancel(true);
             orderRepository.save(order);
             return true;
        }
        else
            return false;
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findByCancelIsFalse();
    }

    @Override
    public List<OrderEntity> getAllOrdersForUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }


}
