package com.dao.connection;

/**
 * This interface describes TransactionManagerFactory contract
 * Created by vlad on 21.03.17.
 */
@FunctionalInterface
public interface TransactionManagerFactory {
    /**
     * Method to get new instance of TransactionManager
     * @return TransactionManager
     */
    TransactionManager createTransactionManager();
}
