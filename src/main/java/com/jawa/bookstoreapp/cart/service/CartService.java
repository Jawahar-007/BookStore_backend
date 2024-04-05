package com.jawa.bookstoreapp.cart.service;

import com.jawa.bookstoreapp.book.entity.BookEntity;
import com.jawa.bookstoreapp.book.repository.BookRepository;
import com.jawa.bookstoreapp.cart.dto.CartDTO;
import com.jawa.bookstoreapp.cart.entity.CartEntity;
import com.jawa.bookstoreapp.cart.repository.CartRepository;
import com.jawa.bookstoreapp.user.entity.UserEntity;
import com.jawa.bookstoreapp.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService implements ICartService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final CartRepository cartRepository;

    @Autowired
    public CartService(UserRepository userRepository, BookRepository bookRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.cartRepository = cartRepository;
    }


    @Override
    public void addToCart(CartDTO cartDTO) {
        UserEntity user = userRepository.findById(cartDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        BookEntity book = bookRepository.findById(cartDTO.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        CartEntity cartEntity = new CartEntity();
        cartEntity.setUser(user);
        cartEntity.setBookEntity(book);
        cartEntity.setBookQuantity(cartDTO.getBookQuantity());
        cartEntity.setBookPrice(book.getBookPrice() * cartDTO.getBookQuantity());

        cartRepository.save(cartEntity);
    }

    @Override
    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public void removeByUserId(long userId) {
        List<CartEntity> deleteId = cartRepository.findByUserId(userId);
        cartRepository.deleteAll(deleteId);
    }

    @Override
    public void updateQuantity(Long cartId, int quantity) {
        CartEntity updateId = cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        BookEntity book = updateId.getBookEntity();
        int bookprice = updateId.getBookPrice();

        updateId.setBookQuantity(quantity);
        updateId.setBookPrice(quantity * bookprice);

        cartRepository.save(updateId);
    }

    @Override
    public List<CartDTO> getAllCartItemsForUser(long userId) {
        List<CartEntity> cartEntities = cartRepository.findByUserId(userId);
        return cartEntities.stream()
                .map(this::displayDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CartDTO> getAllCartItems() {
        List<CartEntity> cartEntities = cartRepository.findAll();
        return cartEntities.stream()
                .map(this::displayDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CartDTO displayDTO(CartEntity cartEntity) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cartEntity.getCartId());
        cartDTO.setUserId(cartEntity.getUser().getId());
        cartDTO.setBookId(cartEntity.getBookEntity().getBookId());
        cartDTO.setBookQuantity(cartEntity.getBookQuantity());
        cartDTO.setBookPrice(cartEntity.getBookPrice());
        return cartDTO;
    }

}
