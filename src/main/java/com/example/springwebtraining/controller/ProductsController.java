package com.example.springwebtraining.controller;

import com.example.springwebtraining.model.Product;
import com.example.springwebtraining.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("products")
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAll(Model model) {
        var products = productService.findAll();
        model.addAttribute("products", products);
        return "products/list";
    }

    @GetMapping("/create")
    public String showCreateForm(@ModelAttribute Product product) {
        return "products/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Product product, Model model) {
        productService.create(product);
        return "redirect:/products";
    }
}
