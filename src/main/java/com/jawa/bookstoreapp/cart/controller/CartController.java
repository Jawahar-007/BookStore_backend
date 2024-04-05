package com.jawa.bookstoreapp.cart.controller;

import com.jawa.bookstoreapp.cart.dto.CartDTO;
import com.jawa.bookstoreapp.cart.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService ;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public void addToCart(@RequestBody CartDTO cartDTO){
        cartService.addToCart(cartDTO);
    }

    // remove a item in the cart by its id
    @DeleteMapping("/remove/{cartid}")
    public void removeFromCart(@PathVariable Long cartid) {
        cartService.removeFromCart(cartid);
    }
    // remove all the cart items for a particular user id
    @DeleteMapping("/removeByUser/{userid}")
    public void removeByUserId(@PathVariable long userid) {
        cartService.removeByUserId(userid);
    }
    // update the quantity of the particular book
    @PutMapping("/updateQuantity/{cartid}/{quantity}")
    public void updateQuantity(@PathVariable Long cartid, @PathVariable int quantity) {
        cartService.updateQuantity(cartid, quantity);
    }
    // Retrieve all the cart items of a particular user
    @GetMapping("/allForUser/{userid}")
    public List<CartDTO> getAllCartItemsForUser(@PathVariable long userid) {
        return cartService.getAllCartItemsForUser(userid);
    }
    // Retrive all cart items
    @GetMapping("/all")
    public List<CartDTO> getAllCartItems() {
        return cartService.getAllCartItems();
    }
}
