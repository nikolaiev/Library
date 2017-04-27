package com.dao.wrapper;

import com.dao.connection.TransactionManager;
import com.dao.connection.TransactionManagerFactoryImpl;

/**
 * Created by vlad on 27.04.17.
 */

@FunctionalInterface
public interface RollbackReadCommitedVoidTransaction {
    default void execute(){
        TransactionManager daoManager = TransactionManagerFactoryImpl
                .getInstance().createTransactionManager();
        daoManager.beginTransaction();
        processMethod(daoManager);
        daoManager.rollbackTransaction();
    }

    void processMethod(TransactionManager transactionManager);
}
