package in.sbuilds.cart.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class FixAmountDiscount implements  Discount{

    private final BigDecimal discountAmount;

    public FixAmountDiscount(BigDecimal discountAmount) {
        this.discountAmount = Objects.requireNonNull(discountAmount, "Discount amount cannot be null");
    }

    @Override
    public BigDecimal applyTo(BigDecimal subtotal) {
        BigDecimal finalAmount = subtotal.subtract(discountAmount);
        return finalAmount.max(BigDecimal.ZERO);
    }
}
