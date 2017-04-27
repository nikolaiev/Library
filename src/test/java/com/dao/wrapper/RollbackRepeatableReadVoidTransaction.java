package com.dao.wrapper;

import com.dao.connection.TransactionManager;
import com.dao.connection.TransactionManagerFactoryImpl;

/**
 * Created by vlad on 27.04.17.
 */
@FunctionalInterface
public interface RollbackRepeatableReadVoidTransaction {
    default void execute(){
        TransactionManager daoManager = TransactionManagerFactoryImpl
                .getInstance().createTransactionManager();
        daoManager.beginRepeatableReadTransaction();
        processMethod(daoManager);
        daoManager.rollbackTransaction();
    }

    void processMethod(TransactionManager transactionManager);
}
