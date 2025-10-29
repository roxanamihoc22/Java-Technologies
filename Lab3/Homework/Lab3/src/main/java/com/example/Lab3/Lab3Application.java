package com.example.Lab3;

import com.example.Lab3.models.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;

@SpringBootApplication
public class Lab3Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Lab3Application.class, args);
	}
    @Autowired
    private OrderService orderService;

    @Override
    public void run(String... args) {
        Order order = new Order(100L, 2L, new BigDecimal("600.00"));
        orderService.calculateFinalPrice(order);
    }

}

