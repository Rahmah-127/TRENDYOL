package com.rahmah.api.controller;

import com.rahmah.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService; // Inject ProductService

    // Get all products
    @GetMapping
    public List<ProductService.Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ProductService.Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    // Search products by keyword
    @GetMapping("/search")
    public List<ProductService.Product> searchProducts(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }
}
