package com.shop.ShoppingStore.services;

import com.shop.ShoppingStore.Model.CartItems;
import com.shop.ShoppingStore.Model.Products;
import com.shop.ShoppingStore.Repository.CartItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CartItemsService {
    @Autowired
    private CartItemsRepository cartItemsRepository;
    @Autowired
    public ProductService productService;

    public CartItems createCartItem(CartItems cartItem) {
        return cartItemsRepository.save(cartItem);
    }

    public CartItems getCartItemById(Long cartItemId) {
        return cartItemsRepository.findById(cartItemId).orElse(null);
    }

    public Iterable<CartItems> getAllCartItems() {
        return cartItemsRepository.findAll();
    }

    public void updateCartItem(CartItems cartItem) {
        cartItemsRepository.save(cartItem);
    }

    public void deleteCartItem(Long cartItemId) {
        cartItemsRepository.deleteById(cartItemId);
    }

    public List<CartItems> getCartItemsByCartId(Long cartId) {
        List<CartItems> allCartItems = cartItemsRepository.findAll();
        List<CartItems> cartItemsWithMatchingCartId = new ArrayList<>();

        for (CartItems cartItem : allCartItems) {
            if (cartItem.getCart_id().getCart_id().equals(cartId)) {
                cartItemsWithMatchingCartId.add(cartItem);
            }
        }

        return cartItemsWithMatchingCartId;
    }

//    public void deleteByProductId(Long productId){
//        cartItemsRepository.deleteByProductId(productId);
//    }

    public double getTotalPrice(Long cartId){
        List<CartItems> items = getCartItemsByCartId(cartId);
        double sum = 0;
        for (CartItems i : items){
            sum += productService.getProductById(i.getProduct_id().getProduct_id()).getPrice() * i.getQuantity();
        }
        return sum;
    }
}
