package com.jawa.bookstoreapp.cart.service;

import com.jawa.bookstoreapp.cart.dto.CartDTO;
import com.jawa.bookstoreapp.cart.entity.CartEntity;

import java.util.List;

public interface ICartService {
    void addToCart(CartDTO cartDTO);

    void removeFromCart(Long cartId);

    void removeByUserId(long userId);

    void updateQuantity(Long cartId, int quantity);

    List<CartDTO> getAllCartItemsForUser(long userId);

    List<CartDTO> getAllCartItems();

    CartDTO displayDTO(CartEntity cartEntity);
}
