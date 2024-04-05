package com.jawa.bookstoreapp.order.entity;


import com.jawa.bookstoreapp.cart.entity.CartEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "order_tbl")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cart;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private Boolean cancel;

    @Column(length = 500)
    private String address;
}
