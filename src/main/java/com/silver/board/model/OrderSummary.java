package com.silver.board.model;

/**
 * Order summary model.
 *
 * Created by sonalw on 17/06/2019.
 */
public class OrderSummary {
    private final double pricePerkg;
    private final double quantity;

    public OrderSummary(double pricePerkg, double quantity) {
        this.pricePerkg = pricePerkg;
        this.quantity = quantity;
    }

    public OrderSummary addQuatity(double quantity) {
        return new OrderSummary(this.pricePerkg, this.quantity + quantity);
    }

    public double getPricePerkg() {
        return pricePerkg;
    }

    public double getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.format("%.2f kg order for £%.2f", quantity, pricePerkg);
    }

}
