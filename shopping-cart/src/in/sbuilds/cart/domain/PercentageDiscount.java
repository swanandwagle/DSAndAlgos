package in.sbuilds.cart.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class PercentageDiscount implements Discount {

    private static final BigDecimal HUNDRED = new BigDecimal("100");
    private final BigDecimal discountRate;

    public PercentageDiscount(BigDecimal discountPercentage) {
        Objects.requireNonNull(discountPercentage, "Discount percentage cannot be null");
        if (discountPercentage.compareTo(BigDecimal.ZERO) <= 0 || discountPercentage.compareTo(HUNDRED) > 0) {
            throw new IllegalArgumentException("Discount percentage must be between 0 (exclusive) and 100 (inclusive), was: " + discountPercentage);
        }
        this.discountRate = discountPercentage.divide(HUNDRED);
    }

    @Override
    public BigDecimal applyTo(BigDecimal subtotal) {
        return subtotal.subtract(subtotal.multiply(discountRate));
    }
}
