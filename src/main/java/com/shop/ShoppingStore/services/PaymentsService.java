package com.shop.ShoppingStore.services;

import com.shop.ShoppingStore.Model.Orders;
import com.shop.ShoppingStore.Model.Payments;
import com.shop.ShoppingStore.Repository.OrdersRepository;
import com.shop.ShoppingStore.Repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class PaymentsService {
    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderService orderService;

    public Payments createPayment(Payments payment) {
        return paymentsRepository.save(payment);
    }

    public Payments getPaymentById(Long paymentId) {
        return paymentsRepository.findById(paymentId).orElse(null);
    }

    public List<Payments> getAllPayments() {
        return paymentsRepository.findAll();
    }


    public Payments initiatePayment(Long orderId) {
        Payments payment = new Payments();
        Orders order = orderService.getOrderById(orderId);
        payment.setPayment_amount(order.getTotal_amount());
        payment.setPayment_date(LocalDateTime.now());
        payment.setPayment_status("PENDING");
        payment.setOrder_id(order);
        return paymentsRepository.save(payment);
    }

    public String completePayment(Long paymentId){
        Payments payment = paymentsRepository.findById(paymentId).orElse(null);
        if(payment != null){
            payment.setPayment_status("COMPLETED");
            Orders order = payment.getOrder_id();
            order.setPayment_status("COMPLETED");
            ordersRepository.save(order);
            paymentsRepository.save(payment);
            return "Payment successfully done";
        }else {
            return "Something went wrong.";
        }
    }

    public String terminatePayment(Long paymentId){
        Payments payment = paymentsRepository.findById(paymentId).orElse(null);
        if(payment != null){
            payment.setPayment_status("CANCELLED");
            Orders order = payment.getOrder_id();
            order.setPayment_status("CANCELLED");
            ordersRepository.save(order);
            paymentsRepository.save(payment);
            return "Payment successfully terminated";
        }else {
            return "Something went wrong.";
        }
    }
}
