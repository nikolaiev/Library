package com.service.impl;

import com.model.entity.user.User;
import com.service.UserService;
import com.service.impl.wrapper.SerializableTransactionWrapper;

/**
 * Created by vlad on 21.03.17.
 */
public class UserServiceImpl extends GenericService implements UserService {

    @Override
    public User createNewUser(SerializableTransactionWrapper<User> wrapper) {
        return executeInSerializableWrapper(wrapper);
    }
}
