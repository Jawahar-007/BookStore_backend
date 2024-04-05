package com.jawa.bookstoreapp.order.service;

import com.jawa.bookstoreapp.order.dto.OrderDTO;
import com.jawa.bookstoreapp.order.entity.OrderEntity;

import java.util.List;


public interface IOrderService {
    OrderEntity placeOrder(OrderDTO orderDTO);
    boolean cancelOrder(Long orderId);
    List<OrderEntity> getAllOrders();
    List<OrderEntity> getAllOrdersForUser(Long userId);

}
