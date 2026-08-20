package in.sbuilds.cart.domain;

import java.math.BigDecimal;

public interface Discount {
    BigDecimal applyTo(BigDecimal subtotal);
}
