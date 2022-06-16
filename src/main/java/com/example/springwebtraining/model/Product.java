package com.example.springwebtraining.model;

import lombok.Data;
import java.util.UUID;

@Data
public class Product {
    private final UUID id = UUID.randomUUID();
    private final String name;
    private final Double price;
    private final Integer stock;

    public Product(String name, Double price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
