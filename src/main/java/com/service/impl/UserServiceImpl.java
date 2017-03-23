package com.service.impl;

import com.dao.TransactionManager;
import com.dao.impl.jdbc.TransactionManagerFactoryImpl;
import com.model.entity.user.User;
import com.model.entity.user.UserRole;
import com.service.UserService;

/**
 * Created by vlad on 21.03.17.
 */
public class UserServiceImpl implements UserService{
    @Override
    public User createNewUser(String login, String password, String name, String soname) {
        TransactionManager manager= TransactionManagerFactoryImpl.getInstance().getTransactionManager();

        return (User) manager.transaction(daoManager->
            daoManager.getUserDao().insert(new User.Builder()
                    .setName(name)
                    .setSoname(soname)
                    .setLogin(login)
                    .setPassword(password)
                    .setRole(UserRole.USER)
                    .build())
        );
    }
}
