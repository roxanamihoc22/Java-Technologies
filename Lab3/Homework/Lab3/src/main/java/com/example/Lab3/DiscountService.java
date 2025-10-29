package com.example.Lab3;

import com.example.Lab3.models.Customer;
import com.example.Lab3.models.Order;

import java.math.BigDecimal;

public interface DiscountService {
    BigDecimal applyDiscount(Customer customer, Order order);
}
