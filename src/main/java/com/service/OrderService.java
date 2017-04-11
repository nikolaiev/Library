package com.service;

import com.controller.commands.dto.OrderList;
import com.model.entity.order.Order;

import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 30.03.17.
 */
public interface OrderService {
    //void createOrder(User user, Book book, OrderType orderType);

    void createOrders(OrderList books, int userId);
    List<Order> getAllOrders();
    void updateOrderStatus(Order order);

    Optional<Order> getOrderById(Integer id);
}
