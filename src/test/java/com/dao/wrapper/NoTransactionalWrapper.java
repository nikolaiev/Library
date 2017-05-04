package com.dao.wrapper;

import com.dao.connection.TransactionManager;
import com.dao.connection.TransactionManagerFactoryImpl;

/**
 * Created by vlad on 04.05.17.
 */
@FunctionalInterface
public interface NoTransactionalWrapper {
    default void execute(){
        TransactionManager daoManager = TransactionManagerFactoryImpl
                .getInstance().createTransactionManager();

        processMethod(daoManager);

    }

    void processMethod(TransactionManager transactionManager);
}
