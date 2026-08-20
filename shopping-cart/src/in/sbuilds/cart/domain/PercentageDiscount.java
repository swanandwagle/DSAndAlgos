package in.sbuilds.cart.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class PercentageDiscount implements  Discount{

    private final BigDecimal discountPercentage;

    public PercentageDiscount(BigDecimal discountPercentage) {
        this.discountPercentage = Objects.requireNonNull(discountPercentage, "Discount percentage cannot be null");
    }

    @Override
    public BigDecimal applyTo(BigDecimal subtotal) {
        return subtotal.subtract(subtotal.multiply(discountPercentage));
    }
}
