package com.shop.ShoppingStore.controllers;

import com.shop.ShoppingStore.Model.CartItems;
import com.shop.ShoppingStore.Model.Orders;
import com.shop.ShoppingStore.Model.Users;
import com.shop.ShoppingStore.Repository.OrdersRepository;
import com.shop.ShoppingStore.services.CartItemsService;
import com.shop.ShoppingStore.services.CartService;
import com.shop.ShoppingStore.services.OrderService;
import com.shop.ShoppingStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderControllerAPI {
    @Autowired
    public OrderService orderService;
    @Autowired
    public UserService userService;
    @Autowired
    public CartItemsService cartItemsService;


    @PostMapping("/createOrder/{userId}/{cartItemsId}")
    public String createOrder(@PathVariable Long userId, @PathVariable Long cartItemsId) {
        return orderService.createNewOrder(userId,cartItemsId);
    }

    @GetMapping("/getAllOrders")
    public List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/getOrderById/{orderId}")
    public Orders getOrderById(@PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/getOrderForCustomer/{userId}")
    public List<Orders> getOrderForCustomer(@PathVariable Long userId){
        return orderService.getOrdersByUserId(userId);
    }

    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping("/getOrdersInDateRange")
    public String getOrdersInDateRange(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") LocalDateTime endDate) {

        List<Orders> ordersInRange = new ArrayList<>();
        List<Orders> allOrders = ordersRepository.findAll();

        for (Orders order : allOrders) {
            LocalDateTime orderDate = order.getOrder_date();
            if (orderDate.isAfter(startDate) && orderDate.isBefore(endDate)) {
                ordersInRange.add(order);
            }
        }

        System.out.println("Fetched " + ordersInRange.size() + " records.");

        return "Fetched " + ordersInRange.size() + " records.";
    }

}
