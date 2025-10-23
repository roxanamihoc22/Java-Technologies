package com.example.Lab2;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductQueries {

    private final JdbcTemplate jdbcTemplate;

    public ProductQueries(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findAll() {
        return jdbcTemplate.query(
                "SELECT id, name, price FROM product",
                (rs, rowNum) -> new Product(rs.getLong("id"), rs.getString("name"), rs.getInt("price"))
        );
    }
}

