package com.silver.board.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Order summary model.
 *
 * Created by sonalw on 17/06/2019.
 */
public class OrderSummary {
    private final BigDecimal pricePerkg;
    private final BigDecimal quantity;

    public OrderSummary(BigDecimal pricePerkg, BigDecimal quantity) {
        this.pricePerkg =  Objects.requireNonNull(pricePerkg);
        this.quantity = Objects.requireNonNull(quantity);
    }

    public BigDecimal getPricePerkg() {
        return pricePerkg;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() != getClass()) {
            return false;
        }
        OrderSummary summary = (OrderSummary) o;
        return Objects.equals(this.quantity, summary.quantity) &&
                Objects.equals(this.pricePerkg, summary.pricePerkg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, pricePerkg);
    }

    @Override
    public String toString() {
        return String.format("%s kg order for £%s", quantity, pricePerkg);
    }

}
