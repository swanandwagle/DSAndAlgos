package in.sbuilds.cart.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class CartItem {
    private final String productName;
    private int quantity;
    private final BigDecimal unitPrice;

    public CartItem(String productName, int quantity, BigDecimal unitPrice) {
        this.productName = Objects.requireNonNull(productName, "productName must not be null");
        Objects.requireNonNull(unitPrice, "unitPrice must not be null");
        if(unitPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Unit price must be positive, was: " + unitPrice);
        }
        this.unitPrice = unitPrice;
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive, was: " + quantity);
        }
        this.quantity = quantity;
    }

    public void increaseQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive, was: " + quantity);
        }
        this.quantity += quantity;
    }

    public void decreaseQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive, was: " + quantity);
        }
        if(this.quantity < quantity) {
            throw new IllegalArgumentException("Existing quantity is less than the quantity to be removed.");
        }
        this.quantity-=quantity;
    }

    public BigDecimal getLineTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}
