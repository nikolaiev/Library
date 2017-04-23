package com.service.impl.wrapper;

import com.dao.connection.TransactionManager;
import com.dao.connection.TransactionManagerFactoryImpl;

/**
 * Created by vlad on 30.03.17.
 */
@FunctionalInterface
public interface SerializableTransactionVoidWrapper {
    default void execute() {
        try (TransactionManager daoManager = TransactionManagerFactoryImpl
                .getInstance().createTransactionManager()) {
            daoManager.beginSerializableTransaction();
            processMethod(daoManager);

        }
    }

    void processMethod(TransactionManager transactionManager);
}
