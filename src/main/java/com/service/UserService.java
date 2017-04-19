package com.service;

import com.model.entity.book.Book;
import com.model.entity.user.User;
import com.service.impl.wrapper.NoTransactionalWrapper;
import com.service.impl.wrapper.SerializableTransactionWrapper;

import java.util.Optional;

/**
 * Created by vlad on 21.03.17.
 */
public interface UserService extends CrudService<User>{
    Optional<User> getUserByLogin(String login);
}
