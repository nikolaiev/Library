package com.dao.impl.jdbc;

import com.dao.DaoManager;
import com.dao.DaoManagerFactory;

/**
 * Created by vlad on 21.03.17.
 */
public class DaoManagerFactoryImpl implements DaoManagerFactory {

    private  DaoManagerFactoryImpl(){}

    private static class LazyInstanceHolder{
        private static final DaoManagerFactory INSTANCE=new DaoManagerFactoryImpl();
    }

    public static DaoManagerFactory getInstance(){
        return LazyInstanceHolder.INSTANCE;
    }

    @Override
    public DaoManager getDaoManager() {
        return new DaoManagerImpl(JdbcPooledDataSource.getInstance());
    }
}
