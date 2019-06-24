package com.silver.board;

import com.silver.board.model.Order;
import com.silver.board.model.OrderSummary;
import com.silver.board.model.OrderType;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
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
        Collector<Order, ?, BigDecimal> sumQuantity = Collectors.reducing(BigDecimal.ZERO, Order::getQuantity ,BigDecimal::add);

        Map<BigDecimal, BigDecimal> priceToQuantityMap = orders.stream()
                .filter(predicate)
                .collect(Collectors.groupingBy(Order::getPricePerkg, sumQuantity));

        return priceToQuantityMap.entrySet().stream()
                .map(entry -> new OrderSummary(entry.getKey(), entry.getValue()))
                .sorted(comparator)
                .collect(Collectors.toList());
    }

}
