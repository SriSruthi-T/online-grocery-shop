package com.grocery.service;

import com.grocery.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceTest {

    private ProductService productService;

    @Before
    public void setUp() {
        productService = new ProductService();
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertNotNull("Product list should not be null", products);
        assertTrue("Product list should have initial items", products.size() > 0);
    }

    @Test
    public void testGetProductById() {
        Product product = productService.getProductById(1);
        assertNotNull("Product with ID 1 should exist", product);
        assertEquals("Basmati Rice (5kg)", product.getName());
    }

    @Test
    public void testGetProductByInvalidId() {
        Product product = productService.getProductById(999);
        assertNull("Product with invalid ID should return null", product);
    }

    @Test
    public void testGetProductsByCategory() {
        List<Product> dairyProducts = productService.getProductsByCategory("Dairy");
        assertNotNull(dairyProducts);
        assertTrue("Should have dairy products", dairyProducts.size() > 0);
        for (Product p : dairyProducts) {
            assertEquals("Dairy", p.getCategory());
        }
    }

    @Test
    public void testSearchProducts() {
        List<Product> results = productService.searchProducts("Rice");
        assertNotNull(results);
        assertTrue("Search for 'Rice' should return results", results.size() > 0);
    }

    @Test
    public void testAddProduct() {
        int initialSize = productService.getAllProducts().size();
        Product newProduct = new Product(0, "Coconut Oil (1L)", "Cooking Oil", 210.00, 50);
        productService.addProduct(newProduct);
        assertEquals(initialSize + 1, productService.getAllProducts().size());
    }

    @Test
    public void testUpdateProduct() {
        Product product = productService.getProductById(1);
        product.setPrice(350.00);
        productService.updateProduct(product);
        Product updated = productService.getProductById(1);
        assertEquals(350.00, updated.getPrice(), 0.01);
    }

    @Test
    public void testDeleteProduct() {
        int initialSize = productService.getAllProducts().size();
        productService.deleteProduct(1);
        assertEquals(initialSize - 1, productService.getAllProducts().size());
        assertNull(productService.getProductById(1));
    }

    @Test
    public void testProductPriceValidation() {
        Product product = productService.getProductById(4);
        assertNotNull(product);
        assertTrue("Product price should be positive", product.getPrice() > 0);
    }

    @Test
    public void testProductQuantityAvailability() {
        Product product = productService.getProductById(7);
        assertNotNull(product);
        assertTrue("Product quantity should be non-negative", product.getQuantity() >= 0);
    }
}