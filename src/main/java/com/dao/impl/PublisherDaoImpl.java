package com.dao.impl;

import com.dao.PublisherDao;
import com.model.entity.book.Publisher;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 20.03.17.
 */
public class PublisherDaoImpl extends AbstractDao<Publisher> implements PublisherDao {
    public PublisherDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Publisher create() throws SQLException {
        return null;
    }

    @Override
    public Publisher insert(Publisher obj) throws SQLException {
        return null;
    }

    @Override
    public void update(Publisher obj) throws SQLException {

    }

    @Override
    public List<Publisher> getAll() throws SQLException {
        return null;
    }

    @Override
    public Optional<Publisher> getById(int key) throws SQLException {
        return null;
    }

    @Override
    public void removeById(int key) throws SQLException {

    }

    @Override
    public Optional<Publisher> getPublisherByTitle(String title) {
        return null;
    }
}
