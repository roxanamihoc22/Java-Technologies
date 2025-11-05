package com.example.Lab3;

import com.example.Lab3.models.Customer;
import com.example.Lab3.models.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Profile("loyal")
public class LoyalDiscountService implements DiscountService {

    private static final BigDecimal discountRate = new BigDecimal("0.50");

    @Override
    public BigDecimal applyDiscount(Customer customer, Order order) {

        if (customer.isLoyal()) {
            BigDecimal discount = order.getTotalAmount().multiply(discountRate);
            return order.getTotalAmount().subtract(discount);
        }
        else {
            return order.getTotalAmount();
        }
    }
}

