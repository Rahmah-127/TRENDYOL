package com.rahmah.api.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product(1, "Wireless Mouse", 19.99));
        products.add(new Product(2, "Bluetooth Headphones", 59.99));
        products.add(new Product(3, "Laptop Stand", 29.99));
        products.add(new Product(4, "Portable Charger", 15.99));
        products.add(new Product(5, "Smartphone Case", 12.49));
        products.add(new Product(6, "Fitness Tracker", 49.99));
        products.add(new Product(7, "Gaming Keyboard", 89.99));
        products.add(new Product(8, "Electric Kettle", 25.99));
        products.add(new Product(9, "Stainless Steel Water Bottle", 16.99));
        products.add(new Product(10, "LED Desk Lamp", 22.49));
        products.add(new Product(11, "Smart Thermostat", 99.99));
        products.add(new Product(12, "Echo Dot", 49.99));
        products.add(new Product(13, "WiFi Router", 39.99));
        products.add(new Product(14, "USB Flash Drive", 9.99));
        products.add(new Product(15, "Bluetooth Speaker", 39.99));
        products.add(new Product(16, "Air Purifier", 89.99));
        products.add(new Product(17, "Smart Light Bulb", 19.99));
        products.add(new Product(18, "Robot Vacuum Cleaner", 199.99));
        products.add(new Product(19, "Electric Toothbrush", 49.99));
        products.add(new Product(20, "Noise Cancelling Headphones", 129.99));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Product> searchProducts(String keyword) {
        return products.stream()
                .filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Inner Product class
    public static class Product {
        private int id;
        private String name;
        private double price;

        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }
}
