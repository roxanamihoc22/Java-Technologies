package com.example.Lab3;

import com.example.Lab3.models.Customer;
import com.example.Lab3.models.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Profile("none")
public class NoDiscountService implements DiscountService {

    @Override
    public BigDecimal applyDiscount(Customer customer, Order order) {
        return order.getTotalAmount();
    }
}


