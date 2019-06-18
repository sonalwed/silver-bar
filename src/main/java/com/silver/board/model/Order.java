package com.silver.board.model;

/**
 * Order model.
 *
 * Created by sonalw on 17/06/2019.
 */
public class Order {

    private final String userId;
    private final double quantity;
    private final double pricePerkg;
    private final OrderType orderType;

    public Order(String userId, double quantity, double pricePerkg, OrderType orderType) {
        this.userId = userId;
        this.quantity = quantity;
        this.pricePerkg = pricePerkg;
        this.orderType = orderType;
    }

    public String getUserId() {
        return userId;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPricePerkg() {
        return pricePerkg;
    }

    public OrderType getOrderType() {
        return orderType;
    }

}
