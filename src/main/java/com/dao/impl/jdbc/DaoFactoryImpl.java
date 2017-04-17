package com.dao.impl.jdbc;

import com.dao.*;

import java.sql.Connection;

/**
 * Created by vlad on 24.03.17.
 */
public class DaoFactoryImpl implements DaoFactory {

    private static class InstanceHolder{
        private static final DaoFactory INSTANCE=new DaoFactoryImpl();
    }

    private DaoFactoryImpl(){}

    public static DaoFactory getInstance(){return  InstanceHolder.INSTANCE;};

    @Override
    public BookDao getBookDao(Connection connection) {
        return BookDaoImpl.getInstance(connection);
    }

    @Override
    public AuthorDao getAuthorDao(Connection connection) {
        return AuthorDaoImpl.getInstance(connection);
    }

    @Override
    public OrderDao getOrderDao(Connection connection) {
        return OrderDaoImpl.getInstance(connection);
    }

    @Override
    public PublisherDao getPublisherDao(Connection connection) {
        return PublisherDaoImpl.getInstance(connection);
    }

    @Override
    public UserDao getUserDao(Connection connection) {
        return UserDaoImpl.getInstance(connection);
    }
}
