package com.example.Lab3.models;

import java.math.BigDecimal;

public class Order {
    private final int id;
    private final Long customerId;
    private final BigDecimal totalAmount;

    public Order(int id, Long customerId, BigDecimal totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
    }

    public int getId() { return id; }
    public Long getCustomerId() { return customerId; }
    public BigDecimal getTotalAmount() { return totalAmount; }
}
