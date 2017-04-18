package com.dao;

/**
 * Created by vlad on 21.03.17.
 */
public interface TransactionManager extends AutoCloseable{
    /**
     * Defines begin of transaction
     */
    void beginTransaction();

    /**
     * Defines begin of transaction with high isolation level
     */
    void beginSerializableTransaction();

    /**
     * Saves transaction.
     */
    void commitTransaction();

    /**
     * rolls back transaction
     */
    void rollbackTransaction();

    /**
     * Closes connection.
     *
     * IMPORTANT.
     * It MUST call ROLLBACK IF TRANSACTION has been begun
     * but was NOT COMMITTED before close method was called, f.e. if any exception was thrown
     */
    @Override
    void close();

    BookDao getBookDao();

    AuthorDao getAuthorDao();

    OrderDao getOrderDao();

    PublisherDao getPublisherDao();

    UserDao getUserDao();
}
