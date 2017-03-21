package com.dao.impl;

import com.dao.AuthorDao;
import com.model.entity.book.Author;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 20.03.17.
 */
public class AuthorDaoImpl extends AbstractDao<Author> implements AuthorDao{

    public AuthorDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<Author> getAuthorByName(String name) {
        return null;
    }

    @Override
    public Author create() throws SQLException {
        return null;
    }

    @Override
    public Author insert(Author obj) throws SQLException {
        return null;
    }

    @Override
    public void update(Author obj) throws SQLException {

    }

    @Override
    public List<Author> getAll() throws SQLException {
        return null;
    }

    @Override
    public Optional<Author> getById(int key) throws SQLException {
        return null;
    }

    @Override
    public void removeById(int key) throws SQLException {

    }
}
