package com.dao;

import com.model.entity.book.Book;
import com.model.entity.user.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 20.03.17.
 */
public interface UserDao {
    Optional<User> getUserByLogin(String login);
}
