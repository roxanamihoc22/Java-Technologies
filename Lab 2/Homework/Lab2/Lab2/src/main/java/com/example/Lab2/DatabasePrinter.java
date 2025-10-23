package com.example.Lab2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabasePrinter implements CommandLineRunner {

    private final ProductQueries productQueries;

    private final Database properties;

    public DatabasePrinter(ProductQueries productQueries, Database properties) {
        this.productQueries = productQueries;
        this.properties = properties;
    }

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    @Value("${app.message}")
    private String message;

    @Override
    public void run(String... args) {
        System.out.println("Database details: "+ properties.getUrl());
        System.out.println("Database content: ");
        productQueries.findAll().forEach(product ->
                System.out.println( product.getId() + " - " + product.getName() + " - " + product.getPrice())
        );
        System.out.println("Active profile: " + activeProfile);
        System.out.println("Message: " + message);
    }
}
