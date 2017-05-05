package com.service.impl.wrapper;

import com.dao.connection.TransactionManager;
import com.dao.connection.TransactionManagerFactoryImpl;

/**
 * Created by vlad on 05.05.17.
 */
@FunctionalInterface
public interface NoTransactionalVoidWrapper {
    default void execute(){
        try (TransactionManager daoManager = TransactionManagerFactoryImpl
                .getInstance().createTransactionManager()) {
            processMethod(daoManager);
        }
    }

    void processMethod(TransactionManager transactionManager);
}
