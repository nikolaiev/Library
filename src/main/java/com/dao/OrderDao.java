package com.dao;

import com.model.entity.order.Order;
import com.model.entity.order.OrderStatus;
import com.model.entity.order.OrderType;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by vlad on 20.03.17.
 */
public interface OrderDao extends GenericDao<Order> {
    List<Order> getOrdersByDate(Date date);
    void updateOrderStatus(Order order);
    List<Order> getOrdersByUserId(int userId);

    List<Order> getOrdersByParams(Integer userId, String bookTitle, OrderStatus orderStatus, OrderType orderType, Date beforeDate, int limit, int offset);

    int getOrdersCountByParams(Integer userId, String bookTitle, OrderStatus orderStatus, OrderType orderType, Date beforeDate);
}
