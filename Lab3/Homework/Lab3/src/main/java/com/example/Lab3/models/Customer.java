package com.example.Lab3.models;

public class Customer {
    private final Long id;
    private final String name;
    private final boolean loyal;

    public Customer(Long id, String name, boolean loyal) {
        this.id = id;
        this.name = name;
        this.loyal = loyal;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public boolean isLoyal() { return loyal; }
}

