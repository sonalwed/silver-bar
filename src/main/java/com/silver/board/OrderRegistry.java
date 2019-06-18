package com.silver.board;

import com.silver.board.model.Order;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.LongSupplier;

/**
 * Order registry.
 *
 * Created by sonalw on 17/06/2019.
 */
public class OrderRegistry {

    private final LongSupplier orderIdSupper;
    private final Map<Long, Order> registry = new HashMap<>();

    public OrderRegistry(LongSupplier orderIdSupper) {
        this.orderIdSupper = orderIdSupper;
    }

    /**
     * Add new orders.
     *
     * @param order new order to add to the registry
     * @return order id, which can be used to cancel the order
     */
    public long addOrder(Order order){
        long id = orderIdSupper.getAsLong();
        this.registry.put(id, order);
        return id;
    }

    /**
     * Cancel order by order ID
     * @param orderId order ID
     */
    public void cancelOrder(long orderId){
        this.registry.remove(orderId);
    }

    public Collection<Order> getAllLiveOrders(){
        return this.registry.values();
    }
}
