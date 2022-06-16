package com.example.springwebtraining.controller.api;

import com.example.springwebtraining.model.PriceRange;
import com.example.springwebtraining.model.Product;
import com.example.springwebtraining.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsRESTController {

    private final ProductService productService;

    public ProductsRESTController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") String id) throws Exception {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> getByPrice(@RequestParam("minPrice") Double minPrice,
                                                    @RequestParam("maxPrice") Double maxPrice) {
        return ResponseEntity.ok(productService.findAllByPrice(new PriceRange(minPrice, maxPrice)));
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.ok(productService.create(product));
    }

}
