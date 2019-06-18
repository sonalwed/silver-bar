package com.silver.board;

import com.silver.board.model.Order;
import com.silver.board.model.OrderType;

import java.util.concurrent.atomic.AtomicLong;


/**
 * Please run this class to demonstrate the live order board functionality.
 *
 * Created by sonalw on 17/06/2019.
 */
public class Main {

    public static void main(String[] args) {
        AtomicLong orderIdGenerator = new AtomicLong();

        OrderAggregator aggregator = new OrderAggregator();
        OrderRegistry registry = new OrderRegistry(orderIdGenerator::incrementAndGet);

        registry.addOrder(new Order("user1", 3.5, 306, OrderType.SELL));
        registry.addOrder(new Order("user2", 1.2, 310, OrderType.SELL));
        registry.addOrder(new Order("user3", 1.5, 307, OrderType.SELL));
        registry.addOrder(new Order("user4", 2.0, 306, OrderType.SELL));
        registry.addOrder(new Order("user5", 2.0, 306, OrderType.BUY));
        registry.addOrder(new Order("user6", 3.0, 306, OrderType.BUY));
        registry.addOrder(new Order("user7", 3.0, 336, OrderType.BUY));


        long cancelOrderId = registry.addOrder(new Order("user7", 3.0, 336, OrderType.BUY));

        registry.cancelOrder(cancelOrderId);

        System.out.println("--- Sell Orders  ---");
        System.out.println(aggregator.aggregateSellOrders(registry.getAllLiveOrders()));

        System.out.println("--- Buy Orders  ---");
        System.out.println(aggregator.aggregateBuyOrders(registry.getAllLiveOrders()));

    }
}
