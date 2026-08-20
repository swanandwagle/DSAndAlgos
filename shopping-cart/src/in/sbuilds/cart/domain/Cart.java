package in.sbuilds.cart.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Cart {
    private Map<String, CartItem> cartItemsByProductName = new HashMap<>();
    private Discount discount;

    public void applyDiscount(Discount discount) {
        this.discount = Objects.requireNonNull(discount, "discount must not be null");
    }

    public void removeDiscount() {
        this.discount = null;
    }

    public Optional<Discount> getDiscount() {
        return Optional.ofNullable(discount);
    }

    public void addToCart(String productName, BigDecimal unitPrice, int quantity) {
        if(productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank, was: " + productName);
        }
        if(cartItemsByProductName.containsKey(productName)) {
            cartItemsByProductName.get(productName).increaseQuantity(quantity);
        } else {
            cartItemsByProductName.put(productName, new CartItem(productName, quantity, unitPrice));
        }
    }

    public void removeFromCart(String productName, int quantity) {
        if(productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank, was: " + productName);
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

    public CartSummary getCartSummary() {
        List<CartItemSnapshot> cartItemList = this
                .cartItemsByProductName
                .values()
                .stream()
                .map(cartItem -> new CartItemSnapshot(
                        cartItem.getProductName(),
                        cartItem.getQuantity(),
                        cartItem.getUnitPrice()))
                .toList();

        BigDecimal subTotal = this.cartItemsByProductName
                .values()
                .stream()
                .map(CartItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal discountedSubTotal = this.getDiscount()
                .map(d -> d.applyTo(subTotal))
                .orElse(subTotal);

        return new CartSummary(cartItemList, subTotal, discountedSubTotal);
    }

}
