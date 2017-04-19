package com.dao.connection;

import com.dao.DaoFactory;
import com.dao.connection.jdbc.JdbcPooledDataSource;
import com.dao.exception.DaoException;
import com.dao.jdbc.DaoFactoryImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by vlad on 21.03.17.
 */
public class TransactionManagerFactoryImpl implements TransactionManagerFactory {
    private static final String LOG_MESSAGE_SQL_CONNECTION_CAN_NOT_BE_NULL =
            "SQL connection can not be null. Datasource returned no connection.";

    private DataSource dataSource= JdbcPooledDataSource.getInstance();
    private DaoFactory daoFactory= DaoFactoryImpl.getInstance();
    private TransactionManagerFactoryImpl(){}

    /*thread safe singleton*/
    private static class LazyInstanceHolder{
        private static final TransactionManagerFactory INSTANCE=new TransactionManagerFactoryImpl();
    }

    public static TransactionManagerFactory getInstance(){
        return LazyInstanceHolder.INSTANCE;
    }

    @Override
    public TransactionManager createTransactionManager() {
        Connection connection;
        try {
            connection=dataSource.getConnection();

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        if(connection==null){
            throw new DaoException()
                    .addLogMessage(LOG_MESSAGE_SQL_CONNECTION_CAN_NOT_BE_NULL);
        }

        return new TransactionManagerImpl(connection,daoFactory);
    }
}
