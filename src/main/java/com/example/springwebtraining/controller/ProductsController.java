package com.example.springwebtraining.controller;

import com.example.springwebtraining.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * TODO
     *  1. GET all products
     *  2. GET a product by id
     *  3. GET products based on price (min/max)
     *  4. CREATE a product
     */

}
