package com.silver.board;

import com.silver.board.model.Order;
import com.silver.board.model.OrderType;


import static java.math.BigDecimal.valueOf;


/**
 * Please run this class to demonstrate the live order board functionality.
 *
 * Created by sonalw on 17/06/2019.
 */
public class Main {

    public static void main(String[] args) {
        OrderAggregator aggregator = new OrderAggregator();
        OrderRegistry registry = new OrderRegistry();

        registry.addOrder(new Order("user1", valueOf(3.5), valueOf(306), OrderType.SELL));
        registry.addOrder(new Order("user2", valueOf(1.2), valueOf(310), OrderType.SELL));
        registry.addOrder(new Order("user3", valueOf(1.5), valueOf(307), OrderType.SELL));
        registry.addOrder(new Order("user4", valueOf(2.0), valueOf(306), OrderType.SELL));
        registry.addOrder(new Order("user5", valueOf(2.0), valueOf(306), OrderType.BUY));
        registry.addOrder(new Order("user6", valueOf(3.0), valueOf(306), OrderType.BUY));
        registry.addOrder(new Order("user7", valueOf(3.0), valueOf(336), OrderType.BUY));

        Order cancelOrder = new Order("user7", valueOf(4.0), valueOf(336), OrderType.BUY);
        registry.addOrder(cancelOrder);

        registry.cancelOrder(cancelOrder);

        System.out.println("--- Sell Orders  ---");
        System.out.println(aggregator.aggregateSellOrders(registry.getAllLiveOrders()));

        System.out.println("--- Buy Orders  ---");
        System.out.println(aggregator.aggregateBuyOrders(registry.getAllLiveOrders()));
    }
}
