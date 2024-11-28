package com.rahmah.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.rahmah.api.service.ProductService;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("cart")
public class ShoppingCartController {

    @Autowired
    private ProductService productService; // Inject ProductService

    // Map to store the shopping cart with product ID and CartItem
    private final Map<Integer, CartItem> shoppingCart = new HashMap<>();

     // Method to add a product to the cart
     @PostMapping("/add")
     public String addToCart(@RequestBody AddToCartRequest request) {
         ProductService.Product product = productService.getProductById(request.getProductId()); // Fetch the product details from ProductService

         if (product == null) {
             return "Product not found!";
         }

         // If the product is already in the cart, update the quantity
         if (shoppingCart.containsKey(request.getProductId())) {
             CartItem existingItem = shoppingCart.get(request.getProductId());
             existingItem.setQuantity(existingItem.getQuantity() + request.getQuantity());
             return "Product ID " + request.getProductId() + " updated with quantity " + existingItem.getQuantity();
         }

         // Otherwise, add the new product to the cart
         shoppingCart.put(request.getProductId(), new CartItem(product, request.getQuantity()));
         return "Product ID " + request.getProductId() + " added to the shopping cart with quantity " + request.getQuantity();
     }

    // 2. Remove a product from the shopping cart
    @DeleteMapping("/{productId}")
    public String removeFromCart(@PathVariable int productId) {
        if (shoppingCart.containsKey(productId)) {
            shoppingCart.remove(productId);
            return "Product ID " + productId + " removed from the shopping cart.";
        } else {
            return "Product ID " + productId + " not found in the shopping cart.";
        }
    }

    // 3. List all items in the shopping cart
    @GetMapping
    public Map<Integer, CartItem> getShoppingCartItems() {
        return shoppingCart;
    }

    // 4. Empty the shopping cart
    @DeleteMapping("/empty")
    public String emptyShoppingCart() {
        shoppingCart.clear();
        return "Shopping cart has been emptied.";
    }

    // 5. Pay for the shopping cart (simulate payment)
    @PostMapping("/pay")
    public String payForShoppingCart() {
        if (shoppingCart.isEmpty()) {
            return "The shopping cart is empty. Add products before proceeding to payment.";
        }

        // Simulate payment process
        shoppingCart.clear();
        return "Payment successful. Your shopping cart has been cleared.";
    }

    public static class CartItem {
        private ProductService.Product product;
        private int quantity;

        public CartItem(ProductService.Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public ProductService.Product getProduct() {
            return product;
        }

        public void setProduct(ProductService.Product product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        // Calculate the total price for this cart item (price * quantity)
        public double getTotalPrice() {
            return product.getPrice() * quantity;
        }
    }

    public static class AddToCartRequest {
        private int productId;
        private int quantity;

        // Getters and Setters
        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}