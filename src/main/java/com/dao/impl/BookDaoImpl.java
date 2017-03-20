package com.dao.impl;

import com.dao.BookDao;
import com.model.entity.book.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 17.03.17.
 */
public class BookDaoImpl extends  AbstractDao<Book> implements BookDao {

    public BookDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public int getCountAvailable() {
        return 0;
    }

    @Override
    public Book create() throws SQLException {
        return null;
    }

    @Override
    public Book insert(Book obj) throws SQLException {
        return null;
    }

    @Override
    public void update(Book obj) throws SQLException {

    }

    @Override
    public List<Book> getAll() throws SQLException {
        return null;
    }

    @Override
    public Optional<Book> getById(int key) throws SQLException {
        return null;
    }

    @Override
    public void removeById(int key) throws SQLException {

    }
}
