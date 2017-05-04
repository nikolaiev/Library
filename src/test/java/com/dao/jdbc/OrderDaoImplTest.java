package com.dao.jdbc;

import com.dao.BookDao;
import com.dao.OrderDao;
import com.dao.connection.TransactionManager;
import com.dao.connection.TransactionManagerFactoryImpl;
import com.dao.connection.jdbc.JdbcPooledDataSource;
import com.model.entity.order.Order;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vlad on 10.04.17.
 */
public class OrderDaoImplTest  extends DaoTest{

    @Test
    public void updateOrderStatus() throws Exception {
    }

    @Test
    public void getAll() throws Exception {

        Connection connection= JdbcPooledDataSource.getInstance().getConnection();
        OrderDao orderDao= DaoFactoryImpl.getInstance().getOrderDao(connection);
        List<Order> orders=orderDao.getAll();

        assertNotNull("Orders list must not be empty",orders);
    }

}