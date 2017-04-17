package com.service.impl.wrapper;

import com.dao.TransactionManager;
import com.dao.impl.jdbc.TransactionManagerFactoryImpl;

/**
 * Created by vlad on 30.03.17.
 */
@FunctionalInterface
public interface TransactionalWrapper <T>{
    default T execute() {
        try (TransactionManager daoManager = TransactionManagerFactoryImpl
                .getInstance().createTransactionManager()) {
            daoManager.beginTransaction();
            T result = processMethod(daoManager);
            daoManager.commitTransaction();
            return result;
        }
    }

    T processMethod(TransactionManager daoManager);
}
