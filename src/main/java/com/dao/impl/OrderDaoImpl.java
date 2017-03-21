package com.dao.impl;

import com.dao.OrderDao;
import com.model.entity.order.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 20.03.17.
 */
public class OrderDaoImpl extends AbstractDao implements OrderDao {

    public OrderDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(){
        return null;
    }

    @Override
    public Order insert(Order obj) {
        return null;
    }

    @Override
    public void update(Order obj) {

    }

    @Override
    public List<Order> getAll(){
        return null;
    }

    @Override
    public Optional<Order> getById(int key){
        return null;
    }

    @Override
    public void removeById(int key) {

    }

    @Override
    public List<Order> getOrdersByDate(Date date) {
        return null;
    }
}
