package com.shop.ShoppingStore.controllers;

import com.shop.ShoppingStore.Model.Payments;
import com.shop.ShoppingStore.services.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/payments")
public class PaymentControllerAPI {
    @Autowired
    private PaymentsService paymentsService;

    @PostMapping("/createPayment/{orderId}")
    public Payments createPayment(@PathVariable Long orderId) {
        return paymentsService.initiatePayment(orderId);
    }

    @PostMapping("/completePayment/{paymentId}")
    public String completePayment(@PathVariable Long paymentId) {
        return paymentsService.completePayment(paymentId);
    }

    @PostMapping("/terminatePayment/{paymentId}")
    public String terminatePayment(@PathVariable Long paymentId) {
        return paymentsService.terminatePayment(paymentId);
    }

    @GetMapping("/getPayment/{paymentId}")
    public Payments getPayment(@PathVariable Long paymentId) {
        return paymentsService.getPaymentById(paymentId);
    }

    @GetMapping("/getAllPayments")
    public List<Payments> getAllPayments() {
        return paymentsService.getAllPayments();
    }
}
