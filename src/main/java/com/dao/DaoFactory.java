package com.dao;

import java.sql.Connection;

/**
 * Created by vlad on 24.03.17.
 */
public interface DaoFactory {
    BookDao getBookDao(Connection connection);

    AuthorDao getAuthorDao(Connection connection);

    OrderDao getOrderDao(Connection connection);

    PublisherDao getPublisherDao(Connection connection);

    UserDao getUserDao(Connection connection);
}
