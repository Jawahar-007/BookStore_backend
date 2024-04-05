package com.jawa.bookstoreapp.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CartDTO {

    private int bookQuantity;
    private int bookPrice;
    private long bookId;
    private long userId;
    private long cartId;
}
