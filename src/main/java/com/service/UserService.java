package com.service;

import com.model.entity.user.User;

import java.util.Optional;

/**
 * Created by vlad on 21.03.17.
 */
public interface UserService extends CrudService<User>{
    Optional<User> getUserByLogin(String login);
}
