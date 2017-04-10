package com.service.impl;

import com.model.entity.user.User;
import com.service.UserService;
import com.service.impl.wrapper.NoTransactionalWrapper;
import com.service.impl.wrapper.SerializableTransactionWrapper;

import java.util.Optional;

/**
 * Created by vlad on 21.03.17.
 */
public class UserServiceImpl extends GenericService implements UserService {
    private static class InstanceHolder{
        private static UserServiceImpl INSTANCE=new UserServiceImpl();

    }

    public static UserService getInstance(){
        return InstanceHolder.INSTANCE;
    }

    @Override
    public User createNewUser(SerializableTransactionWrapper<User> wrapper) {
        return executeInSerializableWrapper(wrapper);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return executeInNonTransactionalWrapper(transactionManager ->
            transactionManager.getUserDao().getUserByLogin(login)
        );
    }
}
