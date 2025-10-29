package com.example.Lab3;

import com.example.Lab3.models.Customer;
import com.example.Lab3.models.Order;
import com.example.Lab3.DiscountService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Profile("largeOrder")
public class OrderDiscountService implements DiscountService {

    private static final BigDecimal threshold = new BigDecimal("500.00");
    private static final BigDecimal discount = new BigDecimal("50.00");

    @Override
    public BigDecimal applyDiscount(Customer customer, Order order) {
        if (order.getTotalAmount().compareTo(threshold) >= 0) {
            return order.getTotalAmount().subtract(discount);
        }
        return order.getTotalAmount();
    }
}
