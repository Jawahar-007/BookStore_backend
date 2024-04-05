package com.jawa.bookstoreapp.order.controller;

import com.jawa.bookstoreapp.order.dto.OrderDTO;
import com.jawa.bookstoreapp.order.entity.OrderEntity;
import com.jawa.bookstoreapp.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping("/orderplace")
    public ResponseEntity<OrderEntity> placeOrder(@RequestBody OrderDTO orderDTO) {
        OrderEntity order = orderService.placeOrder(orderDTO);
        return ResponseEntity.ok(order);
    }
    @PutMapping("/{orderid}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable Long orderid) {

        boolean result = orderService.cancelOrder(orderid);
        if (result) {
            return ResponseEntity.ok().body("Order cancelled successfully.");
        }
        else {
            return ResponseEntity.badRequest().body("Order not found.");
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        List<OrderEntity> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderEntity>> getAllOrdersForUser(@PathVariable Long userId) {
        try {
            List<OrderEntity> orders = orderService.getAllOrdersForUser(userId);
            return ResponseEntity.ok(orders);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
