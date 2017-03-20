package com.dao.impl;

import com.dao.UserDao;
import com.model.entity.user.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 20.03.17.
 */
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create() throws SQLException {
        return null;
    }

    @Override
    public User insert(User obj) throws SQLException {
        return null;
    }

    @Override
    public void update(User obj) throws SQLException {

    }

    @Override
    public List<User> getAll() throws SQLException {
        return null;
    }

    @Override
    public Optional<User> getById(int key) throws SQLException {
        return null;
    }

    @Override
    public void removeById(int key) throws SQLException {

    }
}
