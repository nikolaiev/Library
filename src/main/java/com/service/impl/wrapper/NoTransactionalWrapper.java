package com.service.impl.wrapper;

import com.dao.TransactionManager;
import com.dao.impl.jdbc.TransactionManagerFactoryImpl;

/**
 * Created by vlad on 30.03.17.
 */
@FunctionalInterface
public interface NoTransactionalWrapper <T>{
    default T execute(){
        try (TransactionManager daoManager = TransactionManagerFactoryImpl
                .getInstance().createTransactionManager()) {
            return processMethod(daoManager);
        }
    }

    T processMethod(TransactionManager transactionManager);
}
