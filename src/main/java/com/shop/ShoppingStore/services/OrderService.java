package com.shop.ShoppingStore.services;

import com.shop.ShoppingStore.Model.*;
import com.shop.ShoppingStore.Repository.OrderItemsRepository;
import com.shop.ShoppingStore.Repository.OrdersRepository;
import com.shop.ShoppingStore.Repository.ProductsRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private ProductsRepository productsRepository;
    @Lazy
    @Autowired
    private PaymentsService paymentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemsService cartItemsService;

//    public void createOrderItems(Long productId, int quantity, Long orderId){
//        OrderItems orderItems = new OrderItems();
//        Orders order = ordersRepository.findById(orderId).orElse(null);
//        Products product = productsRepository.findById(productId).orElse(null);
//        orderItems.setOrder_id(order);
//        orderItems.setUnitPrice(product.getPrice());
//        orderItems.setProduct_id(product.getProduct_id());
//        product.setStock_quantity(product.getStock_quantity() - quantity);
//        productsRepository.save(product);
//        orderItemsRepository.save(orderItems);
//    }

    public String createNewOrder(Long userId, Long cartId){
        try {
            Users user = userService.getUserById(userId);
            Orders order = new Orders();
            order.setPayment_status("PENDING");
            order.setOrder_date(LocalDateTime.now());
            order.setShipping_address(user.getShippingAddress());
            order.setTotal_amount(cartItemsService.getTotalPrice(cartId));
            order.setUser_id(userService.getUserById(userId));

            Orders o = ordersRepository.save(order);
            List<CartItems> cartItems = cartItemsService.getCartItemsByCartId(cartId);
            for(CartItems c : cartItems){
                OrderItems orderItems = new OrderItems();
                Products products = c.getProduct_id();
                orderItems.setProduct_id(c.getProduct_id());
                orderItems.setQuantity(c.getQuantity());
                orderItems.setUnitPrice(productsRepository.findById(products.getProduct_id()).orElse(null).getPrice());
                orderItems.setOrder_id(o);
                System.out.println("ORDER ITEMS CREATED");
                orderItemsRepository.save(orderItems);
            }
            return "ORDER SAVED SUCCESSFULLY";
        }catch (Exception e){
            return "error while creating orders";
        }
    }

    public Orders getOrderById(Long orderId){
        return ordersRepository.findById(orderId).orElse(null);
    }

    public List<Orders> getAllOrders(){
        return ordersRepository.findAll();
    }

    public List<Orders> getOrdersByUserId(Long userId){
        List<Orders> list = new ArrayList<>();
        List<Orders> orders = getAllOrders();
        for(Orders o : orders){
            if(Objects.equals(o.getUser_id().getUserId(), userId)){
                list.add(o);
            }
        }
        return list;
    }

    public List<Orders> getOrdersInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Orders> allOrders = ordersRepository.findAll();

        List<Orders> ordersInRange = allOrders.stream()
                .filter(order -> order.getOrder_date().isAfter(startDate) && order.getOrder_date().isBefore(endDate))
                .collect(Collectors.toList());

        return ordersInRange;
    }


}
