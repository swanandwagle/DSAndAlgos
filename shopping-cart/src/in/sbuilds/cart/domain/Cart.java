package in.sbuilds.cart.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<String, CartItem> cartItemsByProductName = new HashMap<>();

    public void addToCart(String productName, BigDecimal unitPrice, int quantity) {
        if(productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank, was: " + quantity);
        }
        if(cartItemsByProductName.containsKey(productName)) {
            cartItemsByProductName.get(productName).increaseQuantity(quantity);
        } else {
            cartItemsByProductName.put(productName, new CartItem(productName, quantity, unitPrice));
        }
    }

    public  void removeFromCart(String productName, int quantity) {
        if(productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank, was: " + quantity);
        }
        if(cartItemsByProductName.containsKey(productName)) {
            CartItem cartItem = cartItemsByProductName.get(productName);
            cartItem.decreaseQuantity(quantity);
            if(cartItem.getQuantity() == 0) {
                cartItemsByProductName.remove(productName);
            }
        } else {
            throw new IllegalArgumentException("Product not in cart: " + productName);
        }
    }

}
