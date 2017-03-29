package com.service.impl;

import com.service.impl.wrapper.NoTransactionalWrapper;
import com.service.impl.wrapper.SerializableTransactionWrapper;
import com.service.impl.wrapper.TransactionalWrapper;

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

    <T> T executeInSerializableWrapper(SerializableTransactionWrapper<T> wrapper){
        return wrapper.execute();
    }
}
