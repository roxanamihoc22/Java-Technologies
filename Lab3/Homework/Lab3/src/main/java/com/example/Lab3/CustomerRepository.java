package com.example.Lab3;

import com.example.Lab3.models.Customer;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerRepository {
    private static final Map<Long, Customer> customers = Map.of(
            1L, new Customer(1L, "Alice", true),
            2L, new Customer(2L, "Bob", false)
    );

    public Customer findById(Long id) {
        return customers.get(id);
    }
}

