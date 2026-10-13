package com.grocery.controller;

import com.grocery.model.CartItem;
import com.grocery.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for shopping cart operations.
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public String viewCart(Model model) {
        List<CartItem> items = cartService.getCartItems();
        double total = cartService.getCartTotal();
        model.addAttribute("cartItems", items);
        model.addAttribute("cartTotal", total);
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam int productId, @RequestParam int quantity) {
        cartService.addToCart(productId, quantity);
        return "redirect:/cart/";
    }

    @PostMapping("/update")
    public String updateQuantity(@RequestParam int itemId, @RequestParam int quantity) {
        cartService.updateQuantity(itemId, quantity);
        return "redirect:/cart/";
    }

    @GetMapping("/remove/{itemId}")
    public String removeFromCart(@PathVariable int itemId) {
        cartService.removeFromCart(itemId);
        return "redirect:/cart/";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart/";
    }
}