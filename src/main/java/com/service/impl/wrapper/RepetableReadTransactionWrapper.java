package com.service.impl.wrapper;

import com.dao.connection.TransactionManager;
import com.dao.connection.TransactionManagerFactoryImpl;

/**
 * Created by vlad on 30.03.17.
 */
@FunctionalInterface
public interface RepetableReadTransactionWrapper<T> {
    default T execute() {
        try (TransactionManager daoManager = TransactionManagerFactoryImpl
                .getInstance().createTransactionManager()) {
            daoManager.beginRepeatableReadTransaction();
            T result = processMethod(daoManager);
            daoManager.commitTransaction();
            return result;
        }
    }

    T processMethod(TransactionManager transactionManager);
}
