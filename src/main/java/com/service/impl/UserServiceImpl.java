package com.service.impl;

import com.model.entity.user.User;
import com.service.UserService;

import java.util.Optional;

/**
 * Created by vlad on 21.03.17.
 */
public class UserServiceImpl extends GenericService implements UserService {

    protected UserServiceImpl() {
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return executeInNonTransactionalWrapper(transactionManager ->
            transactionManager.getUserDao().getUserByLogin(login)
        );
    }


    @Override
    public User create(User user) {
        return  executeInRepeatableReadWrapper(transactionManager ->
                transactionManager.getUserDao().insert(user)
        );
    }

    @Override
    public Optional<User> getById(int userId) {
        return executeInNonTransactionalWrapper(transactionManager ->
                transactionManager.getUserDao().getById(userId)
        );
    }

    @Override
    public void update(User user) {

        //TODO implement
        throw new UnsupportedOperationException();


    }

    @Override
    public void deleteById(int id) {

        //TODO implement
        throw new UnsupportedOperationException();

    }
}
