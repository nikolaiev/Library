package com.service.impl.wrapper;

import com.dao.TransactionManager;
import com.dao.impl.jdbc.TransactionManagerFactoryImpl;

/**
 * Created by vlad on 30.03.17.
 */
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
