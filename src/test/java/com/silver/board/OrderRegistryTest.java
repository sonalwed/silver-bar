package com.silver.board;

import com.silver.board.model.Order;
import com.silver.board.model.OrderType;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.*;

/**
 * Test {@link OrderRegistry}
 *
 * Created by sonalw on 17/06/2019.
 */
public class OrderRegistryTest {
    private static final Order ORDER_ONE  = new Order("user1", 3.5, 306, OrderType.SELL);
    private static final Order ORDER_TWO  = new Order("user1", 7.5, 306, OrderType.SELL);

    private OrderRegistry testable;

    @Before
    public void setUp() {
        AtomicLong orderIdGenerator = new AtomicLong();
        this.testable = new OrderRegistry(orderIdGenerator::incrementAndGet);
    }

    @Test
    public void testAddOrderAnGetLiveOrder() {
        long orderIdFirstOrder = this.testable.addOrder(ORDER_ONE);
        long orderIdSecondOrder = this.testable.addOrder(ORDER_TWO);

        Collection<Order> result = this.testable.getAllLiveOrders();

        assertEquals(1, orderIdFirstOrder);
        assertEquals(2, orderIdSecondOrder);

        assertEquals(2, result.size());

        Set<Order> expected = new HashSet<>();
        expected.add(ORDER_ONE);
        expected.add(ORDER_TWO);
        assertEquals(expected, new HashSet<>(result));
    }

    @Test
    public void testCancelOrder() throws Exception {
        long orderIdFirstOrder = this.testable.addOrder(ORDER_ONE);
        this.testable.addOrder(ORDER_TWO);

        this.testable.cancelOrder(orderIdFirstOrder);
        Collection<Order> result = this.testable.getAllLiveOrders();

        assertEquals(1, result.size());

        Set<Order> expected = new HashSet<>();
        expected.add(ORDER_TWO);
        assertEquals(expected, new HashSet<>(result));
    }

}