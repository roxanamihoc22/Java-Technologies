package com.example.Lab3;

import com.example.Lab3.models.Customer;
import com.example.Lab3.models.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {

    private final CustomerRepository customerRepository;
    private final DiscountService discountService;

    public OrderService(CustomerRepository customerRepository, DiscountService discountService) {
        this.customerRepository = customerRepository;
        this.discountService = discountService;
    }

    public void calculateFinalPrice(Order order) {
        Customer customer = customerRepository.findById(order.getCustomerId());

        BigDecimal beforeDiscount = order.getTotalAmount();
        BigDecimal afterDiscount = discountService.applyDiscount(customer, order);
        BigDecimal discountAmount = beforeDiscount.subtract(afterDiscount);

        System.out.println("Discount type: " + discountService.getClass().getSimpleName() +
                " Customer: " + customer.getName()+
                " Order amount: " + beforeDiscount +
                " Discount: " + discountAmount +
                " After Discount: " + afterDiscount);
    }
}
