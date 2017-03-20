package com.dao;

/**
 * Created by vlad on 21.03.17.
 */
public interface DaoCommand {
    Object execute(DaoManager daoManager);
}