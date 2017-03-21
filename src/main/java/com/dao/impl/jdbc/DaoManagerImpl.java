package com.dao.impl.jdbc;

import com.dao.*;
import com.dao.impl.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by vlad on 21.03.17.
 */
public class DaoManagerImpl implements DaoManager {

    private DataSource dataSource;
    private Connection connection;

    public DaoManagerImpl(DataSource dataSource) {
        this.dataSource =dataSource;
    }

    protected Connection getConnection(){
        if(this.connection == null){
            try {
                this.connection = dataSource.getConnection();
            } catch (SQLException e) {
                return null;
            }
        }
        return this.connection;
    }

    protected Connection getTxConnection() throws SQLException {
        getConnection().setAutoCommit(false);
        return this.connection;
    }

    public Object transaction(DaoCommand command)  {
        try{
            Object returnValue = command.execute(this);
            getConnection().commit();
            return returnValue;
        } catch(Exception e){
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
        return new BookDaoImpl(this.connection);
    }

    @Override
    public AuthorDao getAuthorDao() {
        return new AuthorDaoImpl(this.connection);
    }

    @Override
    public OrderDao getOrderDao() {
        return new OrderDaoImpl(this.connection);
    }

    @Override
    public PublisherDao getPublisherDao() {
        return new PublisherDaoImpl(this.connection);
    }

    @Override
    public UserDao getUserDao() {
        return new UserDaoImpl(this.connection);
    }
}
