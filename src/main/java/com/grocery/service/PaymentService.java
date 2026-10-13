package com.grocery.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public boolean processPayment(String orderId, double amount, String paymentMethod) {
        System.out.println("Processing payment of Rs." + amount + " for order: " + orderId);
        if (amount > 0 && paymentMethod != null) {
            return true;
        }
        return false;
    }

    public String generateInvoice(String orderId) {
        return "INV-GROCERY-" + orderId + "-" + System.currentTimeMillis();
    }
}