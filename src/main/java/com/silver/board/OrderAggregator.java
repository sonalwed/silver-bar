package com.silver.board;

import com.silver.board.model.Order;
import com.silver.board.model.OrderSummary;
import com.silver.board.model.OrderType;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Aggregate orders into {@link OrderSummary} objects
 *
 * Created by sonalw on 17/06/2019.
 */
public class OrderAggregator {

    public List<OrderSummary> aggregateSellOrders(Collection<Order> orders) {
        return aggregate(orders,
                x -> x.getOrderType() == OrderType.SELL,
                Comparator.comparing(OrderSummary::getPricePerkg));
    }

    public List<OrderSummary> aggregateBuyOrders(Collection<Order> orders) {
       return aggregate(orders,
               x -> x.getOrderType() == OrderType.BUY,
               Comparator.comparing(OrderSummary::getPricePerkg).reversed());
    }

    private List<OrderSummary> aggregate(Collection<Order> orders, Predicate<Order> predicate, Comparator<OrderSummary> comparator){
        Map<Double, OrderSummary> result = new HashMap<>();

        for(Order order : orders) {

            if (!predicate.test(order)){
                continue;
            }

            OrderSummary orderSummary = result.get(order.getPricePerkg());

            if (orderSummary == null){
                orderSummary = new OrderSummary(order.getPricePerkg(), order.getQuantity());
            } else {
                orderSummary = orderSummary.addQuatity(order.getQuantity());
            }

            result.put(order.getPricePerkg(), orderSummary);
        }

        return result.values()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

}
