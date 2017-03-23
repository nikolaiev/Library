package com.dao;

/**
 * Created by vlad on 21.03.17.
 */
public interface TransactionManager {
    Object transaction(DaoCommand command);
    Object executeAndClose(DaoCommand command);
    Object transactionAndClose(DaoCommand command);

    BookDao getBookDao();
    AuthorDao getAuthorDao();
    OrderDao getOrderDao();
    PublisherDao getPublisherDao();
    UserDao getUserDao();
}
