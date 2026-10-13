package com.grocery.model;

import java.util.Date;
import java.util.List;

/**
 * Order model representing a customer's grocery order.
 */
public class Order {
    private int orderId;
    private String customerName;
    private String customerEmail;
    private String deliveryAddress;
    private List<CartItem> items;
    private double totalAmount;
    private String status;
    private Date orderDate;

    public Order() {
        this.orderDate = new Date();
        this.status = "PENDING";
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }

    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) {
        this.items = items;
        this.totalAmount = items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
}