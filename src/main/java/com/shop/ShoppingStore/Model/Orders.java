package com.shop.ShoppingStore.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    @SequenceGenerator(name = "orders_seq", sequenceName = "orders_seq", allocationSize = 1)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user_id;

    @Column(name = "order_date")
    private LocalDateTime order_date;

    @Column(name = "total_amount")
    private double total_amount;

    @Column(name = "shipping_address")
    private String shipping_address;

    @Column(name = "payment_status")
    private String payment_status;

    @OneToMany(mappedBy = "order_id", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Users getUser_id() {
        return user_id;
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDateTime order_date) {
        this.order_date = order_date;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", user_id=" + user_id +
                ", order_date=" + order_date +
                ", total_amount=" + total_amount +
                ", shipping_address='" + shipping_address + '\'' +
                ", payment_status='" + payment_status + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }
}

