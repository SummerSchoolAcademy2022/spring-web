package com.example.springwebtraining.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class Product {
    private final UUID id = UUID.randomUUID();

    @NotBlank
    private final String name;

    @Min(0)
    private final Double price;

    @Min(0)
    private final Integer stock;

    public Product(String name, Double price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
