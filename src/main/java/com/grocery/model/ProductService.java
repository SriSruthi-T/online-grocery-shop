package com.grocery.service;

import com.grocery.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for product business logic.
 */
@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();
    private int nextId = 1;

    public ProductService() {
        // Initialize with sample grocery products
        addProduct(new Product(nextId++, "Basmati Rice (5kg)", "Grains & Staples", 320.00, 100));
        addProduct(new Product(nextId++, "Whole Wheat Atta (10kg)", "Grains & Staples", 450.00, 80));
        addProduct(new Product(nextId++, "Toor Dal (1kg)", "Pulses & Lentils", 140.00, 150));
        addProduct(new Product(nextId++, "Fresh Tomatoes (1kg)", "Vegetables", 40.00, 200));
        addProduct(new Product(nextId++, "Onions (1kg)", "Vegetables", 35.00, 300));
        addProduct(new Product(nextId++, "Amul Butter (500g)", "Dairy", 270.00, 60));
        addProduct(new Product(nextId++, "Full Cream Milk (1L)", "Dairy", 65.00, 500));
        addProduct(new Product(nextId++, "Olive Oil (1L)", "Cooking Oil", 650.00, 40));
        addProduct(new Product(nextId++, "Green Tea (100 bags)", "Beverages", 350.00, 90));
        addProduct(new Product(nextId++, "Fresh Apples (1kg)", "Fruits", 180.00, 120));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Product> getProductsByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> searchProducts(String query) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void addProduct(Product product) {
        if (product.getId() == 0) {
            product.setId(nextId++);
        }
        products.add(product);
    }

    public void updateProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == product.getId()) {
                products.set(i, product);
                return;
            }
        }
    }

    public void deleteProduct(int id) {
        products.removeIf(p -> p.getId() == id);
    }
}