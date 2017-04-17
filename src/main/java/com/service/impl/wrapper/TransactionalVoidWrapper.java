package com.service.impl.wrapper;

import com.dao.TransactionManager;
import com.dao.impl.jdbc.TransactionManagerFactoryImpl;

/**
 * Created by vlad on 10.04.17.
 */
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
