package in.sbuilds.cart.domain;

import java.math.BigDecimal;

public record CartItemSnapshot(String productName, int quantity, BigDecimal unitPrice) {
}
