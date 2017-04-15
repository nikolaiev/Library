package com.dao;

import com.model.entity.order.Order;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by vlad on 20.03.17.
 */
public interface OrderDao extends GenericDao<Order> {
    List<Order> getOrdersByDate(Date date);
    void updateOrderStatus(Order order);
}
