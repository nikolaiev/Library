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

    <T> T executeInSerializableWrapper(RepetableReadTransactionWrapper<T> wrapper){
        return wrapper.execute();
    }

    void executeInSerializableVoidWrapper(RepetableReadTransactionVoidWrapper wrapper){
        wrapper.execute();
    }

    void executeInTransactionalVoidWrapper(TransactionalVoidWrapper wrapper){
        wrapper.execute();
    }

}
