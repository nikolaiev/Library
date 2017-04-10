package com.service;

import com.controller.commands.dto.OrderList;

/**
 * Created by vlad on 30.03.17.
 */
public interface OrderService {
    //void createOrder(User user, Book book, OrderType orderType);

    void createOrders(OrderList books, int userId);
}
