package com.silver.board;

import com.silver.board.model.Order;

import java.util.*;

/**
 * Order registry.
 *
 * Created by sonalw on 17/06/2019.
 */
public class OrderRegistry {

    private final List<Order> registry = new LinkedList<>(); // assuming add records happen more often than cancel record

    /**
     * Add new orders.
     *
     * @param order new order to add to the registry
     */
    public void addOrder(Order order){
        this.registry.add(order);
    }

    /**
     * Cancel order
     * @param order order to cancel
     */
    public void cancelOrder(Order order){
        this.registry.remove(order);
    }

    public Collection<Order> getAllLiveOrders(){
        return this.registry;
    }
}
