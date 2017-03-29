package com.dao;

/**
 * Created by vlad on 21.03.17.
 */
@FunctionalInterface
public interface DaoCommand {
    Object execute(TransactionManager manager);
}