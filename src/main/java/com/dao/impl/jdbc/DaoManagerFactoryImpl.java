package com.dao.impl.jdbc;

import com.dao.DaoManager;
import com.dao.DaoManagerFactory;

/**
 * Created by vlad on 21.03.17.
 */
public class DaoManagerFactoryImpl implements DaoManagerFactory {
    @Override
    public DaoManager getDaoManager() {
        return new DaoManagerImpl(JdbcPooledDataSource.getInstance());
    }
}
