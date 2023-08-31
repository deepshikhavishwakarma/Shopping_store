package com.shop.ShoppingStore.controllers;

import com.shop.ShoppingStore.Model.CartItems;
import com.shop.ShoppingStore.Model.Carts;
import com.shop.ShoppingStore.Model.Products;
import com.shop.ShoppingStore.Model.Users;
import com.shop.ShoppingStore.services.CartItemsService;
import com.shop.ShoppingStore.services.CartService;
import com.shop.ShoppingStore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartControllerAPI {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemsService cartItemsService;
    @Autowired
    private ProductService productService;

    @PostMapping("/createCart")
    public Optional<Carts> createCart(@RequestBody Long userId) {
        return cartService.createCart(userId);
    }

    @GetMapping("/GetCartById/{cartId}")
    public Carts getCart(@PathVariable Long cartId) {
        return cartService.getCartById(cartId);
    }

    @GetMapping("/GetAllCarts")
    public List<Carts> getAllCarts() {
        return cartService.getAllCarts();
    }

    @PutMapping("/UpdateCart/{cartId}") //MEANINGLESS ROUTE NOT NEEDED
    public void updateCart(@PathVariable Long cartId, @RequestBody Carts cart) {
        cartService.updateCart(cart);
    }

    @DeleteMapping("/DeleteCart/{cartId}")
    public void deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
    }


    @PostMapping("/addToCart")
    public CartItems addToCart(@RequestBody CartItems cartItem) {
        Carts cart = cartService.getCartById(cartItem.getCart_id().getCart_id());
        Products product = productService.getProductById(cartItem.getProduct_id().getProduct_id());

        if (cart == null || product == null) {
            System.out.println("empty cart");
        }

        cartItem.setCart_id(cart);
        cartItem.setProduct_id(product);

        return cartItemsService.createCartItem(cartItem);
    }

    @DeleteMapping("/removeFromCart/{cartItemId}")
    public void removeFromCart(@PathVariable Long cartItemId) {
        cartService.removeFromCart(cartItemId);
    }



}
