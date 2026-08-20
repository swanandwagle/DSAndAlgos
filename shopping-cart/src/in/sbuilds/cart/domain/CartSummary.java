package in.sbuilds.cart.domain;

import java.math.BigDecimal;
import java.util.List;

public record CartSummary(List<CartItemSnapshot> cartItemList, BigDecimal subTotal, BigDecimal discountedSubTotal) {
}
