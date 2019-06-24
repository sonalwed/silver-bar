package com.silver.board;

import com.silver.board.model.Order;
import com.silver.board.model.OrderSummary;
import com.silver.board.model.OrderType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.*;

/**
 * Test {@link OrderAggregator}
 *
 * Created by sonalw on 17/06/2019.
 */
public class OrderAggregatorTest {

    private OrderAggregator testable;

    @Before
    public void setUp() {
        this.testable= new OrderAggregator();
    }

    @Test
    public void testAggregateSellOrders() {
        List<Order> orders = new ArrayList<>(3);
        orders.add(new Order("user1", valueOf(3.5), valueOf(306), OrderType.SELL));
        orders.add(new Order("user2", valueOf(1.2), valueOf(310), OrderType.SELL));
        orders.add(new Order("user3", valueOf(2.0), valueOf(306), OrderType.SELL));

        List<OrderSummary> result = this.testable.aggregateSellOrders(orders);

        assertEquals(2, result.size());
        assertEquals(new OrderSummary(valueOf(306), valueOf(5.5)), result.get(0));
        assertEquals(new OrderSummary(valueOf(310), valueOf(1.2)), result.get(1));
    }

    @Test
    public void testAggregateBuyOrders() {
        List<Order> orders = new ArrayList<>(3);

        orders.add(new Order("user1", valueOf(3.5), valueOf(306), OrderType.BUY));
        orders.add(new Order("user2", valueOf(1.2), valueOf(310), OrderType.BUY));
        orders.add(new Order("user3", valueOf(2.0), valueOf(306), OrderType.BUY));

        List<OrderSummary> result = this.testable.aggregateBuyOrders(orders);

        assertEquals(2, result.size());
        assertEquals(new OrderSummary(valueOf(310), valueOf(1.2)), result.get(0));
        assertEquals(new OrderSummary(valueOf(306), valueOf(5.5)), result.get(1));
    }
}