package com.shop.ShoppingStore.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "Payments")
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payments_seq")
    @SequenceGenerator(name = "payments_seq", sequenceName = "payments_seq", allocationSize = 1)
    private Long payment_id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order_id;
    private LocalDateTime payment_date;
    private double payment_amount;
    private String payment_status;

    public Long getPayment_id() {
        return payment_id;
    }
    public void setPayment_id(Long payment_id) {
        this.payment_id = payment_id;
    }
    public Orders getOrder_id() {
        return order_id;
    }
    public void setOrder_id(Orders order_id) {
        this.order_id = order_id;
    }
    public LocalDateTime getPayment_date() {
        return payment_date;
    }
    public void setPayment_date(LocalDateTime payment_date) {
        this.payment_date = payment_date;
    }
    public double getPayment_amount() {
        return payment_amount;
    }
    public void setPayment_amount(double payment_amount) {
        this.payment_amount = payment_amount;
    }
    public String getPayment_status() {
        return payment_status;
    }
    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    @Override
    public String toString() {
        return "Payments [payment_id=" + payment_id + ", order_id=" + order_id + ", payment_date=" + payment_date
                + ", payment_amount=" + payment_amount + ", payment_status=" + payment_status + "]";
    }
}

