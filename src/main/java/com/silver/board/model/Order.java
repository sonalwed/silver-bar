package com.silver.board.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Order model.
 *
 * Created by sonalw on 17/06/2019.
 */
public class Order {

    private final String userId;
    private final BigDecimal quantity;
    private final BigDecimal pricePerkg;
    private final OrderType orderType;

    public Order(String userId, BigDecimal quantity, BigDecimal pricePerkg, OrderType orderType) {
        this.userId = Objects.requireNonNull(userId);
        this.quantity = Objects.requireNonNull(quantity);
        this.pricePerkg = Objects.requireNonNull(pricePerkg);
        this.orderType = Objects.requireNonNull(orderType);
    }

    public String getUserId() {
        return userId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getPricePerkg() {
        return pricePerkg;
    }

    public OrderType getOrderType() {
        return orderType;
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
        Order order = (Order) o;
        return Objects.equals(this.userId, order.userId) &&
                Objects.equals(this.quantity, order.quantity) &&
                Objects.equals(this.pricePerkg, order.pricePerkg) &&
                Objects.equals(this.orderType, order.orderType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userId, this.quantity, this.pricePerkg, this.orderType);
    }
}
