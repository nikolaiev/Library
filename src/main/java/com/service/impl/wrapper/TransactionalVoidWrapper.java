package com.service.impl.wrapper;

import com.dao.connection.TransactionManager;
import com.dao.connection.TransactionManagerFactoryImpl;

/**
 * Created by vlad on 10.04.17.
 */
@FunctionalInterface
public interface TransactionalVoidWrapper {
    default void execute() {
        try (TransactionManager daoManager = TransactionManagerFactoryImpl
                .getInstance().createTransactionManager()) {
            daoManager.beginTransaction();
            processMethod(daoManager);
            daoManager.commitTransaction();

        }
    }

    void processMethod(TransactionManager daoManager);
}
