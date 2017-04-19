package com.dao.jdbc;

import com.dao.connection.TransactionManager;
import com.dao.connection.TransactionManagerFactoryImpl;
import com.model.entity.order.Order;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vlad on 10.04.17.
 */
public class OrderDaoImplTest {
    @Test
    public void insert1() throws Exception {
    }

    @Test
    public void update1() throws Exception {
    }

    @Test
    public void updateOrderStatus1() throws Exception {
    }

    @Test
    public void getAll1() throws Exception {
    }

    @Test
    public void getById1() throws Exception {
    }

    @Test
    public void getInstance() throws Exception {
    }

    @Test
    public void insert() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void updateOrderStatus() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
        TransactionManager manager= TransactionManagerFactoryImpl
                .getInstance().createTransactionManager();
        List<Order> orders=manager.getOrderDao().getAll();
        assertNotNull(orders);
    }

    @Test
    public void getById() throws Exception {
    }

    @Test
    public void removeById() throws Exception {
    }

    @Test
    public void getOrdersByDate() throws Exception {
    }

}