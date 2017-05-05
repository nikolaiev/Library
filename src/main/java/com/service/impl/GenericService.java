package com.service.impl;

import com.service.impl.wrapper.*;

/**
 * Created by vlad on 30.03.17.
 */
abstract class GenericService {

    GenericService(){}

    <T> T executeInNonTransactionalWrapper(NoTransactionalWrapper<T> wrapper){
        return wrapper.execute();
    }
    <T> T executeInTransactionalWrapper(TransactionalWrapper<T> wrapper){
        return wrapper.execute();
    }

    <T> T executeInRepeatableReadWrapper(RepeatableReadTransactionWrapper<T> wrapper){
        return wrapper.execute();
    }

    void executeInRepeatableReadVoidWrapper(RepeatableReadTransactionVoidWrapper wrapper){
        wrapper.execute();
    }

    void executeInTransactionalVoidWrapper(TransactionalVoidWrapper wrapper){
        wrapper.execute();
    }

    void executeInNonTransactionalVoidWrapper(NoTransactionalVoidWrapper wrapper){
        wrapper.execute();
    }

}
