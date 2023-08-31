package com.shop.ShoppingStore.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CartItems")
public class CartItems {

    @Id
    @GeneratedValue
    private Long cart_item_id;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Carts cart_id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product_id;
    private Integer quantity;

    public Long getCart_item_id() {
        return cart_item_id;
    }

    public void setCart_item_id(Long cart_item_id) {
        this.cart_item_id = cart_item_id;
    }

    public Carts getCart_id() {
        return cart_id;
    }

    public void setCart_id(Carts cart_id) {
        this.cart_id = cart_id;
    }

    public Products getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Products product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItems [cart_item_id=" + cart_item_id + ", cart_id=" + cart_id + ", product_id=" + product_id
                + ", quantity=" + quantity + "]";
    }
}
