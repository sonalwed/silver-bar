package com.silver.board;

import com.silver.board.model.Order;
import com.silver.board.model.OrderSummary;
import com.silver.board.model.OrderType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test {@link OrderAggregator}
 *
 * Created by sonalw on 17/06/2019.
 */
public class OrderAggregatorTest {

    private OrderAggregator testable = new OrderAggregator();

    @Test
    public void testAggregateSellOrders() {
        List<Order> orders = new ArrayList<>(3);
        orders.add(new Order("user1", 3.5, 306, OrderType.SELL));
        orders.add(new Order("user2", 1.2, 310, OrderType.SELL));
        orders.add(new Order("user3", 2.0, 306, OrderType.SELL));

        List<OrderSummary> result = this.testable.aggregateSellOrders(orders);

        assertEquals(2, result.size());
        assertEquals(306, result.get(0).getPricePerkg(), 0.1);
        assertEquals(5.5, result.get(0).getQuantity(), 0.01);
        assertEquals(310, result.get(1).getPricePerkg(), 0.1);
        assertEquals(1.2, result.get(1).getQuantity(), 0.01);
    }

    @Test
    public void testAggregateBuyOrders() {
        List<Order> orders = new ArrayList<>(3);

        orders.add(new Order("user1", 3.5, 306, OrderType.BUY));
        orders.add(new Order("user2", 1.2, 310, OrderType.BUY));
        orders.add(new Order("user3", 2.0, 306, OrderType.BUY));

        List<OrderSummary> result = this.testable.aggregateBuyOrders(orders);

        assertEquals(2, result.size());
        assertEquals(310, result.get(0).getPricePerkg(), 0.1);
        assertEquals(1.2, result.get(0).getQuantity(), 0.01);
        assertEquals(306, result.get(1).getPricePerkg(), 0.1);
        assertEquals(5.5, result.get(1).getQuantity(), 0.01);
    }
}