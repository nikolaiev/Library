package com.dao.impl.jdbc;

import com.dao.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by vlad on 21.03.17.
 */
public class TransactionManagerImpl implements TransactionManager {

    //private DataSource dataSource;
    private Connection connection;
    private DaoFactory daoFactory;

    /*public TransactionManagerImpl(DataSource dataSource) {
        this.dataSource =dataSource;
    }*/

    public TransactionManagerImpl(Connection connection, DaoFactory daoFactory) {
        this.connection = connection;
        this.daoFactory = daoFactory;
    }

    protected Connection getConnection(){
        /*if(this.connection == null){
            try {
                this.connection = dataSource.getConnection();
            } catch (SQLException e) {
                return null;
            }
        }*/
        return this.connection;
    }

    protected Connection getTxConnection() throws SQLException {
        getConnection().setAutoCommit(false);
        return this.connection;
    }


    public Object transaction(DaoCommand command)  {
        try{
            getTxConnection();
            Object returnValue = command.execute(this);
            getConnection().commit();
            return returnValue;
        } catch(Exception e){
            e.printStackTrace();
            try {
                getConnection().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Object executeAndClose(DaoCommand command) {
        try{
            getConnection();
            return command.execute(this);
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Object transactionAndClose(DaoCommand command){
        return executeAndClose(daoManager -> daoManager.transaction(command));
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
}
