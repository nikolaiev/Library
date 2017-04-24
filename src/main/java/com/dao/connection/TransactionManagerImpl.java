package com.dao.connection;

import com.dao.*;
import com.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by vlad on 21.03.17.
 */
public class TransactionManagerImpl implements TransactionManager {
    private static final String LOG_MESSAGE_CAN_NOT_BEGIN_TRANSACTION = "Can not begin transaction.";
    private static final String LOG_MESSAGE_CAN_NOT_COMMIT_TRANSACTION = "Can not commit transaction";
    private static final String LOG_MESSAGE_CAN_NOT_ROLLBACK_TRANSACTION = "Can not rollback transaction";
    private static final String LOG_MESSAGE_CAN_NOT_CLOSE_CONNECTION = "Can not close connection";

    private static final int DEFAULT_TRANSACTION_ISOLATION_LEVEL = Connection.TRANSACTION_READ_COMMITTED;
    private static final int REPEATABLE_TRANSACTION_ISOLATION_LEVEL = Connection.TRANSACTION_REPEATABLE_READ;

    private Connection connection;
    private DaoFactory daoFactory;
    private boolean transactionActive = false;


    public TransactionManagerImpl(Connection connection, DaoFactory daoFactory) {
        this.connection = connection;
        this.daoFactory = daoFactory;
    }



    @Override
    public void beginTransaction() {
        beginTransactionWithIsolationLevel(DEFAULT_TRANSACTION_ISOLATION_LEVEL);
    }

    private void beginTransactionWithIsolationLevel(int transactionIsolationLevel) {
        try {
            connection.setTransactionIsolation(transactionIsolationLevel);
            connection.setAutoCommit(false);
            transactionActive = true;
        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_CAN_NOT_BEGIN_TRANSACTION);
        }
    }


    @Override
    public void beginRepeatableReadTransaction() {
        beginTransactionWithIsolationLevel(REPEATABLE_TRANSACTION_ISOLATION_LEVEL);
    }

    @Override
    public void commitTransaction() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
            transactionActive = false;
        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_CAN_NOT_COMMIT_TRANSACTION);
        }

    }

    @Override
    public void rollbackTransaction() {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
            transactionActive = false;
        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_CAN_NOT_ROLLBACK_TRANSACTION);
        }
    }

    @Override
    public void close() {
        if (transactionActive) {
            rollbackTransaction();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_CAN_NOT_CLOSE_CONNECTION);
        }
    }


    @Override
    public BookDao getBookDao() {
        return daoFactory.getBookDao(this.connection);
    }

    @Override
    public AuthorDao getAuthorDao() {
        return daoFactory.getAuthorDao(this.connection);
    }

    @Override
    public OrderDao getOrderDao() {
        return daoFactory.getOrderDao(this.connection);
    }

    @Override
    public PublisherDao getPublisherDao() {
        return daoFactory.getPublisherDao(this.connection);
    }

    @Override
    public UserDao getUserDao() {
        return daoFactory.getUserDao(this.connection);
    }

    protected Connection getConnection(){
        return this.connection;
    }

}

