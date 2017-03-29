package com.service;

import com.model.entity.user.User;
import com.service.impl.wrapper.SerializableTransactionWrapper;

/**
 * Created by vlad on 21.03.17.
 */
public interface UserService {
    User createNewUser(SerializableTransactionWrapper<User> wrapper);
}
