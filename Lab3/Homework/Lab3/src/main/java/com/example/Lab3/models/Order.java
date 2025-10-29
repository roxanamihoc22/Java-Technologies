package com.example.Lab3.models;

import java.math.BigDecimal;

public class Order {
    private final Long id;
    private final Long customerId;
    private final BigDecimal totalAmount;

    public Order(Long id, Long customerId, BigDecimal totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
    }

    public Long getId() { return id; }
    public Long getCustomerId() { return customerId; }
    public BigDecimal getTotalAmount() { return totalAmount; }
}
