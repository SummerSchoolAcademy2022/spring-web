package com.example.springwebtraining.service;

import com.example.springwebtraining.model.PriceRange;
import com.example.springwebtraining.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final List<Product> mockedDb = new ArrayList<>(Arrays.asList(
            new Product("iPhone 12", 3500d, 4),
            new Product("iPhone 13 Pro", 6543d, 5),
            new Product("MacBook Pro 13\"", 7200d, 6),
            new Product("MacBook 16\"", 9100d, 7)
    ));

    public List<Product> findAll() {
        return mockedDb;
    }

    public Product findById(String id) throws Exception {
        return mockedDb.stream()
                .filter(product -> id.equals(product.getId().toString()))
                .findFirst()
                .orElseThrow(Exception::new);
    }

    public List<Product> findAllByPrice(PriceRange priceRange) {
        return mockedDb.stream().filter(product -> isProductInPriceRange(priceRange, product)).toList();
    }

    private boolean isProductInPriceRange(PriceRange priceRange, Product product) {
        return product.getPrice() >= priceRange.minPrice() && product.getPrice() <= priceRange.maxPrice();
    }

    public Product create(Product product) {
        mockedDb.add(product);
        return mockedDb.get(mockedDb.size() - 1);
    }

}
