package com.service;

import com.model.entity.user.User;

/**
 * Created by vlad on 21.03.17.
 */
public interface UserService {
    User createNewUser(String login,String password,String name,String soname);
}
