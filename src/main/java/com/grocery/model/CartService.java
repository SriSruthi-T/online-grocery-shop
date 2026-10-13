package com.grocery.service;

import com.grocery.model.CartItem;
import com.grocery.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for shopping cart business logic.
 */
@Service
public class CartService {

    @Autowired
    private ProductService productService;

    private List<CartItem> cartItems = new ArrayList<>();
    private int nextItemId = 1;

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public void addToCart(int productId, int quantity) {
        Product product = productService.getProductById(productId);
        if (product != null && quantity > 0) {
            for (CartItem item : cartItems) {
                if (item.getProduct().getId() == productId) {
                    item.setQuantity(item.getQuantity() + quantity);
                    return;
                }
            }
            CartItem newItem = new CartItem(product, quantity);
            newItem.setId(nextItemId++);
            cartItems.add(newItem);
        }
    }

    public void updateQuantity(int itemId, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getId() == itemId) {
                if (quantity <= 0) {
                    removeFromCart(itemId);
                } else {
                    item.setQuantity(quantity);
                }
                return;
            }
        }
    }

    public void removeFromCart(int itemId) {
        cartItems.removeIf(item -> item.getId() == itemId);
    }

    public void clearCart() {
        cartItems.clear();
    }

    public double getCartTotal() {
        return cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public int getCartItemCount() {
        return cartItems.stream().mapToInt(CartItem::getQuantity).sum();
    }
}