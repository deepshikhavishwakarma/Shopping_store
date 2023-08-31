package com.shop.ShoppingStore.services;

import com.shop.ShoppingStore.Model.CartItems;
import com.shop.ShoppingStore.Model.Carts;
import com.shop.ShoppingStore.Model.Products;
import com.shop.ShoppingStore.Model.Users;
import com.shop.ShoppingStore.Repository.CartItemsRepository;
import com.shop.ShoppingStore.Repository.CartsRepository;
import com.shop.ShoppingStore.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartsRepository cartsRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public Optional<Carts> createCart(Long userId){
        Users user = userService.getUserById(userId);
        if(user != null){
            Carts cart = new Carts();
            cart.setCreated_at(LocalDateTime.now());
            cart.setUser(user);
            return Optional.of(cartsRepository.save(cart));
        }else {
            return Optional.empty();
        }
    }

    public Carts getCartById(Long cartId) {
        return cartsRepository.findById(cartId).orElse(null);
    }

    public List<Carts> getAllCarts() {
        return cartsRepository.findAll();
    }

    public void updateCart(Carts cart) {
        cartsRepository.save(cart);
    }

    public void deleteCart(Long cartId) {
        cartsRepository.deleteById(cartId);
    }

    // Add more methods for updating, retrieving, and deleting carts

    public CartItems addToCart(Carts cart, Products product, int quantity) {
        CartItems cartItem = new CartItems();
        cartItem.setCart_id(cart);
        cartItem.setProduct_id(product);
        cartItem.setQuantity(quantity);
        return cartItemsRepository.save(cartItem);
    }

    public void removeFromCart(Long cartItemId) {
        cartItemsRepository.deleteById(cartItemId);
    }


}
