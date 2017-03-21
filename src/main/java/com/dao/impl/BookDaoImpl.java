package com.dao.impl;

import com.dao.BookDao;
import com.model.entity.book.Author;
import com.model.entity.book.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 17.03.17.
 */
public class BookDaoImpl extends  AbstractDao implements BookDao{

    public BookDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public int getCountAvailable() {
        return 0;
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        return null;
    }

    @Override
    public Book create(){
        return null;
    }

    @Override
    public Book insert(Book obj) {
        return null;
    }

    @Override
    public void update(Book obj){

    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Optional<Book> getById(int key) {
        return null;
    }

    @Override
    public void removeById(int key)  {

    }
}
