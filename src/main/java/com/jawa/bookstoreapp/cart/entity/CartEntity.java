package com.jawa.bookstoreapp.cart.entity;

import com.jawa.bookstoreapp.book.entity.BookEntity;
import com.jawa.bookstoreapp.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "cart_tbl")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    private Long cartId;
    @Column(name = "quantity")
    private int bookQuantity;
    @Column(name = "price")
    private int bookPrice;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity bookEntity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


}
