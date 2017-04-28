package com.dao.jdbc;

import com.dao.wrapper.RollbackReadCommitedVoidTransaction;
import com.dao.wrapper.RollbackRepeatableReadVoidTransaction;

/**
 * Helper class to process DAO tests
 * Created by vlad on 27.04.17.
 */
public class DaoTest {
    void executeInReadCommitedVoidRollbackWrapper(RollbackReadCommitedVoidTransaction wrapper){
        wrapper.execute();
    }

    void executeInRepeatableReadVoidRollbackWrapper(RollbackRepeatableReadVoidTransaction wrapper){
        wrapper.execute();
    }
}
